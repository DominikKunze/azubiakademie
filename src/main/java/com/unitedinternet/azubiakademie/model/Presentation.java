package com.unitedinternet.azubiakademie.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Object of an entry
 *
 * @author dkunze
 * @version 1.0
 */
@SuppressWarnings({"unused", "DanglingJavadoc"})
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    /** Id of the entry */
    protected Long id;
    /** Date of the entry */
    protected String date;
    /** First speaker of the entry **/
    protected String speaker1;
    /** Second speaker of the entry */
    protected String speaker2;
    /** Third speaker of the entry */
    protected String speaker3;
    /** First topic of the entry */
    protected String topic1;
    /** Second topic of the entry */
    protected String topic2;
    /** Third topic of the entry */
    protected String topic3;
    /** Status of the first presentation */
    protected int status1;
    /** Status of the second presentation **/
    protected int status2;
    /** Status of the third presentation */
    protected int status3;
    /** Notes on lectures */
    protected String comment;

    /**
     * Constructor without parameters
     */
    private Presentation(){

    }

    /**
     * Constructor with parameters
     * @param   date
     *          Date as String
     * @param   speaker1
     *          Name of the first speaker as String
     * @param   speaker2
     *          Name of the second speaker as String
     * @param   speaker3
     *          Name of the third speaker as String
     * @param   topic1
     *          Topic of the first presentation as String
     * @param   topic2
     *          Topic of the second presentation as String
     * @param   topic3
     *          Topic of the third presentation as String
     * @param   comment
     *          Notes on lectures
     */
    public Presentation(String date, String speaker1, String speaker2, String speaker3, String topic1, String topic2,
                        String topic3, String comment){
        this.date = date;
        this.speaker1 = speaker1;
        this.speaker2 = speaker2;
        this.speaker3 = speaker3;
        this.topic1 = topic1;
        this.topic2 = topic2;
        this.topic3 = topic3;
        this.comment = comment;
    }
}
