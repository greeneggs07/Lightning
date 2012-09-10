/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import zj.taskmanager.model.Agenda;

/**
 *
 * @author Owner
 */
public class XmlMediator {
    private static JAXBContext context;
    private static Marshaller m;
    private static Unmarshaller um;

    public XmlMediator() throws JAXBException {
        context = JAXBContext.newInstance(Agenda.class);
        um = context.createUnmarshaller(); 
        m = context.createMarshaller(); 
    }
    
        
    public static Agenda getAgenda(String filePath)throws JAXBException{
        Agenda agenda;
        try {      
            agenda =(Agenda) um.unmarshal(new FileReader(filePath));
        } catch (FileNotFoundException ex) {
            agenda = new Agenda();
        }
        return agenda;
    }
    
    public static void writeAgenda(String filePath, Agenda agenda) throws JAXBException, IOException{
        Writer w = null;
        try {
            w = new FileWriter(filePath);
            m.marshal(agenda, w);
        } finally {
            try {
                w.close();
            } catch (Exception e) {
            }
        }
    }
}
