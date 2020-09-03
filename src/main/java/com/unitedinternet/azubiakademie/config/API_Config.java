package com.unitedinternet.azubiakademie.config;

/**
 * API Configuration (temporary security solution - can be removed when the ldap link is created)
 *
 * @author dkunze
 * @version 1.0
 */
public class API_Config {
    /** Permission to delete entrys */
    public static final boolean ALLOW_DELETE = true;
    /** Permission to edit entrys */
    public static final boolean ALLOW_EDIT = true;
    /** Permission to add new entrys */
    public static final boolean ALLOW_ADD = true;

    /** Permission to edit the speakers */
    public static final boolean ALLOW_EDIT_SPEAKER = true;
    /** Permission to edit the topics */
    public static final boolean ALLOW_EDIT_TOPIC = true;
    /** Permission to edit the status */
    public static final boolean ALLOW_EDIT_STATUS = true;
    /** Permission to edit the date */
    public static final boolean ALLOW_EDIT_DATE = true;
    /** Permission to edit the comment */
    public static final boolean ALLOW_EDIT_COMMENT = true;
}
