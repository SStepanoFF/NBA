package framework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.xerces.dom.DeferredElementImpl;
import org.testng.ITestContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 */
public class XMLDataProvider {

    private final String dataDelimiter = "¡¡";
    private final String methodDelimiter = "``";
    public HashMap<String, String> MethodData = new HashMap<String, String>();
    public String tcName = "";

    private String getXMLPathOfTC(String tcName) {
        File filePath = new File(getClass().getClassLoader().getResource(".").getPath() + "\\testdata");
        String fileName = tcName + ".xml";
        try {
            boolean recursive = true;
            Collection files = FileUtils.listFiles(filePath, null, recursive);
            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().equals(fileName))
                    return file.getAbsolutePath();
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return "NF";
    }

    public int getTestMethodIterations(int testCaseIteration){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String methodName = stackTraceElements[2].getMethodName();
        //get value from hash map
        String key = tcName + methodDelimiter + testCaseIteration + methodDelimiter + methodName + methodDelimiter + "\\d+";
        int retVal = Integer.parseInt(getHashMethodIteration(key));
        return retVal;
    }

    public void loadData(ITestContext context) {
        tcName = context.getCurrentXmlTest().getName();
        String xmlPath = getXMLPathOfTC(tcName);
        readXML(xmlPath, tcName);
    }

    private void readXML(String xmlPath, String tcName) {
        if (xmlPath == "") {

        }
        else {
            try {
                File xmlFile = new File(xmlPath);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setIgnoringElementContentWhitespace(true);
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document xmlDoc = builder.parse(xmlFile);
                int testCaseCounter = 0;
                DeferredElementImpl parentElement = (DeferredElementImpl) xmlDoc.getDocumentElement();
                addToHashMap(parentElement.getNodeName() + methodDelimiter + parentElement.getChildElementCount() + dataDelimiter + "");
                for (int i = 0; i < parentElement.getChildNodes().getLength(); i++) {
                    //inside test case tag
                    Node node = parentElement.getChildNodes().item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        //iteration of test case starts
                        testCaseCounter++;
                        for (int j = 0; j < node.getChildNodes().getLength(); j++) {
                            int methodCounter = 0;
                            Node metNode = node.getChildNodes().item(j);
                            //select method
                            if (metNode.getNodeType() == Node.ELEMENT_NODE){
                                //iteration over test case method starts
                                int iterationCount = getMethodIterationCount(metNode);
                                //add method and its total iterations to map
                                addToHashMap(parentElement.getNodeName() + methodDelimiter + testCaseCounter + methodDelimiter + metNode.getNodeName() + methodDelimiter + iterationCount + dataDelimiter + "");
                                //loop through fields in the method
                                for (int k = 0; k < metNode.getChildNodes().getLength(); k++) {
                                    Node iterationNode = metNode.getChildNodes().item(k);
                                    if (iterationNode.getNodeType() == Node.ELEMENT_NODE) {
                                        //inside method iteration tag checking field nodes
                                        methodCounter++;
                                        String tempStr = parentElement.getNodeName() + methodDelimiter + testCaseCounter + methodDelimiter + metNode.getNodeName() + methodDelimiter + methodCounter + methodDelimiter;
                                        for (int fieldCounter = 0; fieldCounter < iterationNode.getChildNodes().getLength() ; fieldCounter++){
                                           Node fieldNode = iterationNode.getChildNodes().item(fieldCounter);
                                           if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
                                               //field node, check if node is sub-method
                                               if (!(((DeferredElementImpl)fieldNode).getFirstElementChild() != null)) {
                                                   addToHashMap(tempStr + fieldNode.getNodeName() + dataDelimiter + fieldNode.getTextContent());
                                               }
                                               else {
                                                   //sub-method iteration
                                                   int subMethodCounter = 0, subMethodIterationCount = getMethodIterationCount(fieldNode);
                                                   addToHashMap(tempStr + fieldNode.getNodeName() + methodDelimiter + subMethodIterationCount + dataDelimiter + "");
                                                   for (int subMetCounter = 0; subMetCounter < fieldNode.getChildNodes().getLength(); subMetCounter++) {
                                                       Node subMethodIteration = fieldNode.getChildNodes().item(subMetCounter);
                                                       if (subMethodIteration.getNodeType() == Node.ELEMENT_NODE) {
                                                           //inside iteration node, loop through sub-method field nodes
                                                           subMethodCounter++;
                                                           for (int subMetFieldIterator = 0; subMetFieldIterator < subMethodIteration.getChildNodes().getLength(); subMetFieldIterator++){
                                                               Node subMetField = subMethodIteration.getChildNodes().item(subMetFieldIterator);
                                                               if (subMetField.getNodeType() == Node.ELEMENT_NODE) {
                                                                   String tempStrNew = tempStr + fieldNode.getNodeName() + methodDelimiter + subMethodCounter + methodDelimiter;
                                                                   addToHashMap(tempStrNew + subMetField.getNodeName() + dataDelimiter + subMetField.getTextContent());
                                                               }
                                                           }
                                                       }
                                                   }
                                               }
                                           }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                System.out.print(ex.toString());
            }
        }
    }

    public static int getMethodIterationCount(Node node) {
        int counter = 0;
        if (node.getChildNodes().getLength() > 0) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (node.getChildNodes().item(i).getNodeName() == "iteration") {
                    counter++;
                }
            }
            return counter;
        }
        return 1;
    }

    private void addToHashMap(String line) {
        if (!MethodData.containsKey(line.split(dataDelimiter)[0])) {
            String[] arr = line.split(dataDelimiter);
            MethodData.put(arr[0], arr.length > 1 ? arr[1] : "" );
        }
    }

    public void cleanXMLData() {
        MethodData.clear();
    }

    public Object getMethodData(String className, int iteration, String methodName) {
        try{
            Class<?> returnModel = Class.forName(className);
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String callingTestStep = stackTraceElements[2].getMethodName();
            Object objModel = returnModel.newInstance();
            Field[] fields = returnModel.getFields();
            HashMap<String, String> objMap = getFieldsHashMap(callingTestStep, methodName, iteration + 1);
            //set fields
            for (Field f : fields ) {
               for (Map.Entry<String, String> entry : objMap.entrySet()){

               }
            }
        }
        catch (Exception ex) {
            System.out.print(ex.toString());
        }
        return  null;
    }

    private HashMap<String, String> getFieldsHashMap(String callingMethodName, String methodName, int iteration) {
        HashMap<String, String> objMap = new HashMap<String, String>();
        String requiredKey = tcName + methodDelimiter + "1" + methodDelimiter + callingMethodName + methodDelimiter + iteration;
        for (Map.Entry<String, String> oldEntry : MethodData.entrySet()) {
           if (oldEntry.getKey().contains(requiredKey)) {
               String[] newKey = oldEntry.getKey().split(methodDelimiter);
               objMap.put(newKey[newKey.length - 1], oldEntry.getValue());
           }
        }
        return objMap;
    }

    private String getHashValue(String key) {
        Pattern pattern = Pattern.compile(key);
        for (Map.Entry<String, String> entry : MethodData.entrySet()) {
            String myKey = entry.getKey();
            Matcher m = pattern.matcher(myKey);
            if (m.matches()) {
                //found key
                return entry.getValue();
            }
        }
        return "Key or value not found";
    }

    private String getHashMethodIteration(String key) {
        Pattern pattern = Pattern.compile(key);
        for (Map.Entry<String, String> entry : MethodData.entrySet()) {
            String myKey = entry.getKey();
            Matcher m = pattern.matcher(myKey);
            if (m.matches()) {
                //found key
                String[] arr = myKey.split(methodDelimiter);
                return arr[arr.length - 1];
            }
        }
        return "Key or value not found";
    }
}