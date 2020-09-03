package com.unitedinternet.azubiakademie.libary;

import com.unitedinternet.azubiakademie.config.API_Config;
import org.springframework.ui.Model;

/**
 * RuleToModelAdder adds all configurated rules of the api to the model.
 *
 * @author dkunze
 * @version 1.0
 */
public class RuleToModelAdder {
    /**
     * adds all rules to the model.
     * @param   model
     *          The function of model is to get informations from the backend to the frondend.
     */
    @SuppressWarnings("unused")
    public void addRulesFromAPIToModel(Model model){
        model.addAttribute("api_rules",new Object(){
            public boolean add = API_Config.ALLOW_ADD;
            public boolean delete = API_Config.ALLOW_DELETE;
            public boolean edit = API_Config.ALLOW_EDIT;
            public boolean editSpeaker = API_Config.ALLOW_EDIT_SPEAKER;
            public boolean editTopic = API_Config.ALLOW_EDIT_TOPIC;
            public boolean editStatus = API_Config.ALLOW_EDIT_STATUS;
            public boolean editComment = API_Config.ALLOW_EDIT_COMMENT;
            public boolean editDate = API_Config.ALLOW_EDIT_DATE;
        });
    }
}
