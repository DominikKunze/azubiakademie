package com.unitedinternet.azubiakademie.boundary;

import com.unitedinternet.azubiakademie.config.API_Config;
import com.unitedinternet.azubiakademie.libary.EntrySorter;
import com.unitedinternet.azubiakademie.libary.RuleToModelAdder;
import com.unitedinternet.azubiakademie.model.Presentation;
import com.unitedinternet.azubiakademie.services.data.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The API resources is responsible for the HTTP interface, through which the entries are managed.
 *
 * @author dkunze
 * @version 1.0
 */
@Controller
@RequestMapping("/api")
public class ApiResource {
    @Autowired
    PresentationRepository presentationRepository;

    RuleToModelAdder ruleToModelAdder = new RuleToModelAdder();
    /**
     * This function adds a new entrie. The function is called via the http-request-method "post".
     * @param   date
     *          Day of the presentation as String in the format "yyyy-mm-dd".
     * @param   speaker1
     *          Name of the first speaker as String.
     * @param   speaker2
     *          Name of the second speaker as String.
     * @param   speaker3
     *          Name of the third speaker as String.
     * @param   topic1
     *          Topic of the first presentation as String.
     * @param   topic2
     *          Topic of the second presentation as String.
     * @param   topic3
     *          Topic of the third presentation as String.
     * @param   comment
     *          Notes on lectures
     * @param   model
     *          The function of model is to get information's from the backend to the frontend.
     * @return  index.html - Redirects to the index page.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPresentation(@RequestParam("date") String date, @RequestParam("speaker1") String speaker1,
                                  @RequestParam("speaker2") String speaker2, @RequestParam("speaker3") String speaker3,
                                  @RequestParam("topic1") String topic1, @RequestParam("topic2") String topic2,
                                  @RequestParam("topic3") String topic3, @RequestParam("comment") String comment,
                                  Model model){
        // rules for the API (temporary security solution - can be removed when the ldap link is created)
        ruleToModelAdder.addRulesFromAPIToModel(model);
        if(!API_Config.ALLOW_ADD){ // Checks whether it is allowed to add an entry
            model.addAttribute("error", "Diese Aktion wurde deaktiviert!");
            model.addAttribute("allPresentations", EntrySorter.getFuturePresentations(presentationRepository));
            return "index.html";
        }

        // checks passed parameters
        if(!date.isEmpty()) {
            String[] dateSplit = date.split("-");

            if(dateSplit.length == 3) {
                if (dateSplit[0].length() == 4 && dateSplit[1].length() == 2 && dateSplit[2].length() == 2){
                    Presentation presentation = new Presentation(date, speaker1, speaker2, speaker3, topic1, topic2, topic3, comment);
                    presentationRepository.save(presentation); // add new entry to database

                    model.addAttribute("success","Eintrag wurden erfolgreich erstellt!");
                }else{
                    model.addAttribute("error","Eintrag konnte nicht erstellt werden!");
                }
            }else{
                model.addAttribute("error","Eintrag konnte nicht erstellt werden!");
            }
        }else{
            model.addAttribute("error","Eintrag konnte nicht erstellt werden!");
        }
        model.addAttribute("allPresentations",EntrySorter.getFuturePresentations(presentationRepository));
        return "index.html";
    }

    /**
     * This function deletes aa entry. In the url it is required to provide an id of a entry. The function is called via the http-request-method "post".<br>
     * <i>Example: serverDomain/2/delete - entry with the id 2 will get deleted</i>
     * @param   id
     *          Id of the entry that shall be deleted
     * @param   model
     *          The function of model is to get information's from the backend to the frontend.
     * @return  index.html - Redirects to the index page.
     */
    @RequestMapping("{presId}/delete")
    public String removePresentation(@PathVariable("presId") Long id, Model model){
        // rules for the API (temporary security solution - can be removed when the ldap link is created)
        ruleToModelAdder.addRulesFromAPIToModel(model);
        if(!API_Config.ALLOW_DELETE){
            model.addAttribute("error", "Diese Aktion wurde deaktiviert!");
            model.addAttribute("allPresentations", EntrySorter.getFuturePresentations(presentationRepository));
            return "index.html";
        }

        presentationRepository.deleteById(id); // deletes entry from the database

        model.addAttribute("allPresentations", EntrySorter.getFuturePresentations(presentationRepository));
        model.addAttribute("success","Eintrag wurde erfolgreich entfernt!");
        return "index.html";
    }

    /**
     * This function edits an entry. The function is called via the http-request-method "post".
     * @param   date
     *          Day of the presentation as String in the format "yyyy-mm-dd".
     * @param   speaker1
     *          Name of the first speaker as String.
     * @param   speaker2
     *          Name of the second speaker as String.
     * @param   speaker3
     *          Name of the third speaker as String.
     * @param   topic1
     *          Topic of the first presentation as String.
     * @param   topic2
     *          Topic of the second presentation as String.
     * @param   topic3
     *          Topic of the third presentation as String.
     * @param   status1
     *          Status of the first presentation as Integer.
     * @param   status2
     *          Status of the second presentation as Integer.
     * @param   status3
     *          Status of the third presentation as Integer.
     * @param   comment
     *          Notes on lectures.
     * @param   id
     *          Id of the entry that shall be edited.
     * @param   model
     *          The function of model is to get information's from the backend to the frontend.
     * @return  index.html - Redirects to the index page.
     */
    @RequestMapping("/edit")
    public String editPresentation(@RequestParam("date") String date, @RequestParam("speaker1") String speaker1,
                                   @RequestParam("speaker2") String speaker2, @RequestParam("speaker3") String speaker3,
                                   @RequestParam("topic1") String topic1, @RequestParam("topic2") String topic2,
                                   @RequestParam("topic3") String topic3, @RequestParam("status1") int status1,
                                   @RequestParam("status2") int status2, @RequestParam("status2") int status3,
                                   @RequestParam("comment") String comment, @RequestParam("id") Long id, Model model){
        // rules for the API (temporary security solution - can be removed when the ldap link is created)
        ruleToModelAdder.addRulesFromAPIToModel(model);
        if(!API_Config.ALLOW_EDIT){
            model.addAttribute("error", "Diese Aktion wurde deaktiviert!");
            model.addAttribute("allPresentations", EntrySorter.getFuturePresentations(presentationRepository));
            return "index.html";
        }


        @SuppressWarnings("OptionalGetWithoutIsPresent") Presentation presentation = presentationRepository.findById(id).get();
        if(API_Config.ALLOW_EDIT_DATE) presentation.setDate(date);
        if(API_Config.ALLOW_EDIT_SPEAKER) {
            presentation.setSpeaker1(speaker1);
            presentation.setSpeaker2(speaker2);
            presentation.setSpeaker3(speaker3);
        }
        if(API_Config.ALLOW_EDIT_TOPIC) {
            presentation.setTopic1(topic1);
            presentation.setTopic2(topic2);
            presentation.setTopic3(topic3);
        }
        if(API_Config.ALLOW_EDIT_STATUS) {
            presentation.setStatus1(status1);
            presentation.setStatus2(status2);
            presentation.setStatus3(status3);
        }
        if(API_Config.ALLOW_EDIT_COMMENT) presentation.setComment(comment);
        presentationRepository.save(presentation);

        model.addAttribute("success","Eintrag wurde erfolgreich bearbeitet!");
        model.addAttribute("allPresentations",EntrySorter.getFuturePresentations(presentationRepository));
        return "index.html";
    }
}
