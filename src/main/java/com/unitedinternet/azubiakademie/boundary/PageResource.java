package com.unitedinternet.azubiakademie.boundary;

import com.unitedinternet.azubiakademie.libary.EntrySorter;
import com.unitedinternet.azubiakademie.libary.RuleToModelAdder;
import com.unitedinternet.azubiakademie.model.Presentation;
import com.unitedinternet.azubiakademie.services.data.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The page resource is responsible for forwarding to the respective html files.
 *
 * @author dkunze
 * @version 1.0
 */
@Controller
public class PageResource {
    @Autowired
    PresentationRepository presentationRepository;

    RuleToModelAdder ruleToModelAdder = new RuleToModelAdder();
    /**
     * @param   model
     *          The function of model is to get information's from the backend to the frontend.
     * @return  index.html - Redirects to the index page.
     */
    @RequestMapping(path = {"index.html","index","/","home","/page/","/page/index.html","/page/index","/page/home"})
    public String linkToGoHome(Model model) {
        ruleToModelAdder.addRulesFromAPIToModel(model);
        model.addAttribute("allPresentations",EntrySorter.getFuturePresentations(presentationRepository));
        return "index.html";
    }

    /**
     * @return  neuerEintrag.html - Redirect to the add page
     */
    @RequestMapping("/page/addPresentation")
    public String linkToAddPresentation(){
        return "neuerEintrag.html";
    }

    /**
     * @param   id
     *          Id of the entry that shall be edited
     * @param   model
     *          The function of model is to get information's from the backend to the frontend.
     * @return  edit.html - Redirect to the edit page
     */
    @RequestMapping("/page/{presId}/edit")
    public String linkToEdit(@PathVariable("presId")Long id, Model model){
        @SuppressWarnings("OptionalGetWithoutIsPresent") Presentation presentation = presentationRepository.findById(id).get();

        ruleToModelAdder.addRulesFromAPIToModel(model);
        model.addAttribute("pres",presentation);
        return "edit.html";
    }

    /**
     * @param   model
     *          The function of model is to get information's from the backend to the frontend.
     * @return  historyList.html - Redirect to the history list
     */
    @RequestMapping("/page/history")
    public String linkToHistory(Model model) {
        ruleToModelAdder.addRulesFromAPIToModel(model);
        model.addAttribute("allPresentations",EntrySorter.getPastPresentations(presentationRepository));
        return "historyList.html";
    }
}
