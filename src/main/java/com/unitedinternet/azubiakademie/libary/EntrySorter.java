package com.unitedinternet.azubiakademie.libary;

import com.unitedinternet.azubiakademie.model.Presentation;
import com.unitedinternet.azubiakademie.services.data.PresentationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * EntrySorter contains the functions to order entrys by date.
 *
 * @author dkunze
 * @version 1.0
 */
public class EntrySorter {
    /**
     * This function returns all entrys in the future ordered by date.
     * @param   presentationRepository
     *          List of all entrys.
     * @return  Collection
     */
    public static Collection<Presentation> getFuturePresentations(PresentationRepository presentationRepository){
        ArrayList<Presentation> futurePresentations = new ArrayList<>();
        Map<Integer, Presentation> orderMap = new HashMap<>();

        // find all entrys in the future
        for (Presentation presentation : presentationRepository.findAll()) {
            Date presDate = null;

            try {
                presDate = new SimpleDateFormat("yyyy-MM-dd").parse(presentation.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date currentTime = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(currentTime);
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            assert presDate != null;
            if (calendar.getTime().before(presDate)) {
                orderMap.put(Integer.parseInt(presentation.getDate().replaceAll("-", "")), presentation);
            }
        }

        //order by date
        TreeMap<Integer, Presentation> sorted = new TreeMap<>(orderMap);
        for (Map.Entry<Integer, Presentation> entry : sorted.entrySet()) {
            futurePresentations.add(entry.getValue());
        }

        return futurePresentations;
    }

    /**
     * This function returns all entrys in the past ordered by date.
     * @param   presentationRepository
     *          List of all entrys.
     * @return  Collection
     */
    public static Collection<Presentation> getPastPresentations(PresentationRepository presentationRepository) {
        ArrayList<Presentation> futurePresentations = new ArrayList<>();
        Map<Integer, Presentation> orderMap = new HashMap<>();

        // find all entrys in the past
        for (Presentation presentation : presentationRepository.findAll()) {
            Date presDate = null;
            try {
                presDate = new SimpleDateFormat("yyyy-MM-dd").parse(presentation.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date currentTime = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(currentTime);
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            assert presDate != null;
            if (calendar.getTime().after(presDate)) {
                orderMap.put(Integer.parseInt(presentation.getDate().replaceAll("-", "")), presentation);
            }
        }

        //order by date
        TreeMap<Integer, Presentation> sorted = new TreeMap<>(orderMap);
        for (Map.Entry<Integer, Presentation> entry : sorted.entrySet()) {
            futurePresentations.add(entry.getValue());
        }

        return futurePresentations;
    }
}
