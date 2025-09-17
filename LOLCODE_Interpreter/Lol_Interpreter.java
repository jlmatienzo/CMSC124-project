package LOLCODE_Interpreter;

import java.util.regex.Pattern;
import java.util.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTextArea;

public class Lol_Interpreter {
    
    boolean obtwFlag;
    String obtwStr="";
    boolean errorFlag;
    boolean haiFlag;
    boolean kthxFlag=false;
    boolean compFlag;
    boolean orlyFlag=false;
    boolean yarlyFlag=false;
    boolean nowaiFlag=false;
    boolean oicFlag=false;
    boolean wtfFlag=false;
    boolean omgFlag=false;
    boolean gtfoFlag=false;
    boolean omgwtfFlag=false;
    boolean oicWtfFlag=false;
    Hashtable datatypeTable = new Hashtable();
    JTextArea outputTerminal;
    DefaultTableModel model;
    DefaultTableModel model1;
    
    public Lol_Interpreter(
            JTextArea outputTerminal,
            DefaultTableModel lexemeModel,
            DefaultTableModel symbolModel,
            File file
    ){
        this.outputTerminal = outputTerminal;
        this.model = lexemeModel;
        this.model1 = symbolModel;
        scanFile(file);
    }

    public void checkToken(String expected,String currKey, String currValue, int lineCounter){
        errorMsg = "Syntax ERROR: Expected: "+ expected+"; Got: "+currKey+"("+currValue+") at line "+lineCounter;
                errorFlag=true;
                //function for making error statements
    }
    
    public boolean isOperator(String curr,Pattern[] opArray){
         for(int i=0;i<opArray.length;i++)
               if(opArray[i].matcher(curr).matches())return true;  
         return false;
         //checks if a keyword is an operator and returns true or false
         //used in operation grammar
    }
    
      public boolean isBool(String curr,Pattern[] boolArray){
         for(int i=0;i<boolArray.length;i++)
               if(boolArray[i].matcher(curr).matches())return true;  
         return false;
         //checks if a keyword is boolean operator and returns true or false
         //used in boolean operation grammar
    }
    
    public boolean isVar_Lit(String curr){
      if(curr.equals("VARIDENT")|| curr.equals("NUMBR_LITERAL")||
              curr.equals("NUMBAR_LITERAL")||curr.equals("YARN_LITERAL")) return true;
      return false;
      //checks if a lexeme is a variable or a literal
      //used in operation grammar
    }
    public boolean isVar_Troof(String curr){
          if(curr.equals("VARIDENT")||curr.equals("TROOF_LITERAL")) return true;
          return false;
             //checks if a lexeme is a variable or boolean type
             //used in boolean operations grammar
    }
      
    public boolean isVar_Lit_Troof(String curr){
          if(curr.equals("VARIDENT")|| curr.equals("NUMBR_LITERAL")||
                  curr.equals("NUMBAR_LITERAL")||curr.equals("YARN_LITERAL")||curr.equals("TROOF_LITERAL")) return true;
          return false;
             //checks if a lexeme is a variable, number, string or boolean type
             //used in visible grammar
    }
    public boolean isLit_Troof(String curr){
          if(curr.equals("NUMBR_LITERAL")||
                  curr.equals("NUMBAR_LITERAL")||curr.equals("YARN_LITERAL")||curr.equals("TROOF_LITERAL")) return true;
          return false;
             //checks if a lexeme is a variable, number, string or boolean type
             //used in visible grammar
    }
    
    public Number calculate(Number curr, String op, Number num){
        
        switch(op){
        
            case "SUM OF":
                if(curr instanceof Float && num instanceof Float)
                    return new Float(num.floatValue()+curr.floatValue());
                else if(curr instanceof Float && num instanceof Integer)
                    return new Float(num.intValue()+curr.floatValue());
                else if(num instanceof Float && curr instanceof Integer)
                    return new Float(num.floatValue() + curr.intValue());
                else if(num instanceof Integer && curr instanceof Integer)
                    return new Integer(num.intValue() + curr.intValue());
               
            case "DIFF OF":
               if(curr instanceof Float && num instanceof Float)
                    return new Float(num.floatValue()-curr.floatValue());
                else if(curr instanceof Float && num instanceof Integer)
                    return new Float(num.intValue()-curr.floatValue());
                else if(num instanceof Float && curr instanceof Integer)
                    return new Float(num.floatValue() - curr.intValue());
                else if(num instanceof Integer && curr instanceof Integer)
                    return new Integer(num.intValue() - curr.intValue());
                
            case "PRODUKT OF":
                if(curr instanceof Float && num instanceof Float)
                    return new Float(num.floatValue()*curr.floatValue());
                else if(curr instanceof Float && num instanceof Integer)
                    return new Float(num.intValue()*curr.floatValue());
                else if(num instanceof Float && curr instanceof Integer)
                    return new Float(num.floatValue() *curr.intValue());
                else if(num instanceof Integer && curr instanceof Integer)
                    return new Integer(num.intValue() * curr.intValue());
                
            case "QUOSHUNT OF":
               if(curr.intValue()==0)curr=curr.floatValue();
               if(curr instanceof Float && num instanceof Float)
                    return new Float(num.floatValue()/curr.floatValue());
                else if(curr instanceof Float && num instanceof Integer)
                    return new Float(num.intValue()/curr.floatValue());
                else if(num instanceof Float && curr instanceof Integer)
                    return new Float(num.floatValue() / curr.intValue());
                else if(num instanceof Integer && curr instanceof Integer)
                    return new Integer(num.intValue() / curr.intValue());
            case "MOD OF":
                if(curr.intValue()==0)curr=curr.floatValue();
                if(curr instanceof Float && num instanceof Float)
                    return new Float(num.floatValue()%curr.floatValue());
                else if(curr instanceof Float && num instanceof Integer)
                    return new Float(num.intValue()%curr.floatValue());
                else if(num instanceof Float && curr instanceof Integer)
                    return new Float(num.floatValue() % curr.intValue());
                else if(num instanceof Integer && curr instanceof Integer)
                   return new Integer(num.intValue() % curr.intValue());
            case "BIGGR OF":
                if(curr instanceof Float && num instanceof Float)
                    if(num.floatValue()>=curr.floatValue())return num.floatValue();
                    else return curr.floatValue();
                else if(curr instanceof Float && num instanceof Integer)
                    if(num.intValue()>=curr.floatValue())return num.intValue();
                    else return curr.floatValue();
                else if(num instanceof Float && curr instanceof Integer)
                    if(num.floatValue()>=curr.intValue())return num.floatValue();
                    else return curr.intValue();
                else if(num instanceof Integer && curr instanceof Integer)
                    if(num.intValue()>=curr.intValue())return num.intValue();
                    else return curr.intValue();
                case "SMALLR OF":
                if(curr instanceof Float && num instanceof Float)
                    if(num.floatValue()<=curr.floatValue())return num.floatValue();
                    else return curr.floatValue();
                else if(curr instanceof Float && num instanceof Integer)
                    if(num.intValue()<=curr.floatValue())return num.intValue();
                    else return curr.floatValue();
                else if(num instanceof Float && curr instanceof Integer)
                    if(num.floatValue()<=curr.intValue())return num.floatValue();
                    else return curr.intValue();
                else if(num instanceof Integer && curr instanceof Integer)
                    if(num.intValue()<=curr.intValue())return num.intValue();
                    else return curr.intValue();
            default:
                break;
        
        }
    return 0;
    }
    public String removeQuotes(String s){
         String temp="";
     if(null != s && !s.isEmpty()) {
         for(int i=0; i<s.length();i++){
            if(s.charAt(i)!='"'){
                temp+=s.charAt(i);
            }
         }
     }
    return temp;
    }
    
    public boolean isInteger(String s,int lineCounter, String key) {
   
      String temp = removeQuotes(s);
        
    try { 
        if(key.equals("NUMBR_LITERAL"))Integer.parseInt(temp); 
        else if(key.equals("NUMBAR_LITERAL"))Float.parseFloat(temp);
        else if((key.equals("YARN_LITERAL")||key.equals("VARIDENT"))&&s.contains("."))Float.parseFloat(temp);
        else if((key.equals("YARN_LITERAL")||key.equals("VARIDENT"))&&!s.contains("."))Integer.parseInt(temp);
    } catch(NumberFormatException e) {
        errorMsg="ERROR: Cannot parse NUMBR from: "+s+" at line "+lineCounter;
        return false; 
    } catch(NullPointerException e) {
        errorMsg="ERROR: Cannot parse NUMBR from empty string: "+s+" at line "+lineCounter;
        return false;
    }
    
    return true;
}
   
       public Number parseNum(String s, String key) {
   
      //String temp = "";
       
        if(key.equals("NUMBR_LITERAL")){return Integer.parseInt(s); }
        else if(key.equals("NUMBAR_LITERAL"))return Float.parseFloat(s);
        else if((key.equals("YARN_LITERAL")||key.equals("VARIDENT"))&&s.contains("."))return Float.parseFloat(s);
         else if((key.equals("YARN_LITERAL")||key.equals("VARIDENT"))&&!s.contains("."))return Integer.parseInt(s);
 return 0;
    
    
}     public String concatenate(String prevS,String currS) {
           return prevS+currS;
       }
      //
   /****SMOOSH******/
      public boolean smooshGrammar(String prevValue,String currKey,String currValue,Stack st, Stack val,int lineCounter,boolean start, boolean last,Hashtable symbolTable,ArrayList<String> mainValueList){
         boolean error=false;
         
       System.out.println("current:"+currValue);
        System.out.println(st);
       
        if(st.empty()&&!start && prevValue.equals("")&&last) {//if it tries to push an invalid value when the stack is empty, the function will produce an error
            System.out.println("erroooor");
            checkToken("endline", currKey, currValue,lineCounter);
            return false;
        }
    
        else if (currValue.equals("SMOOSH")){//if the current value is SMOOSH
            //it will only be pushed if it is the first value in the stack
            if(st.empty()){
                st.push(currValue);
               val.push(currKey);
            }
           // else if(isVar_Troof(st.peek().toString())) error=true;
            else error=true;
        }
        
        else if(currValue.equals("AN")){
             System.out.println("AN FOUND");
             
            if(st.empty()&&last) error=true;
            else if(st.empty()&&!last){
                System.out.println("correct: "+prevValue);
                if(!prevValue.equals("")){
                    System.out.println("push");
                    st.push(symbolTable.get(removeQuotes(prevValue)));
                }
                val.push(currKey);
            }
            
            else if(isVar_Lit_Troof(val.peek().toString())){//if previous is a literal
                st.push(currValue);
                val.push(currKey);
                System.out.println("smoosh operand valid");
                
            }
        }
        
    
        else if(isVar_Lit_Troof(currKey)){
           // if(currValue.equals("IT")){}
           System.out.println("????");
            if(st.empty()){
                System.out.println("empty");
                return false;
            }

            else if(st.peek().equals("AN")){//if previous is AN
                
                System.out.println("prev is an");
//                prevValue=concatenate(st.peek().toString(),currValue);
                System.out.println(val);
                System.out.println(prevValue);
               
                st.pop();
                prevValue=concatenate(st.pop().toString(),currValue);
                
                if(st.size()==1){
                    st.pop();
                    val.pop();
                }
               
                val.pop();
                val.pop();//pop smoosh
                st.push(prevValue);
                val.push(currKey);
                System.out.println("stack after pop: "+st);
               if(orlyFlag!=true&&wtfFlag!=true){
                symbolTable.put("IT","\""+prevValue+"\"");
                datatypeTable.put("IT", "YARN_LITERAL");
               }
                if(last){
                    val.pop();
                    st.pop();
                    datatypeTable.put("IT", "YARN_LITERAL");
                }
            }
            
            
            else if(isVar_Lit_Troof(val.peek().toString())){
                System.out.println("errr");
                error=true;
                
            }//if previous is a literal
            else if(st.peek().equals("SMOOSH")){
                st.push(currValue);
                val.push(currKey);
                 System.out.println("prev is smoosh");
            }
            else{
                System.out.println("errrwaaan");
                error=true;
            }
          
        }
        
        else error=true;
        
        if (!st.empty()&&error){
            if(isVar_Lit_Troof(val.peek().toString()))
                checkToken("KEYWORD(AN)", currKey, currValue,lineCounter);
            
            else if(st.peek().equals("AN"))
                checkToken("literal", currKey, currValue,lineCounter);
            
            return false;
        
        }
       return true;     
     
     }
        
    public boolean smooshGrammarIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter,Hashtable symbolTable,Pattern[] opArray){
        Stack st = new Stack();//this function iterates over a given line in the code with the "SMOOSH" as start variable
        Stack val= new Stack();
        boolean start=true;//then sends parameters to another function word by word for processing
        boolean last=false;
        ArrayList<String> opKeyList =new ArrayList();
        ArrayList<String> opValueList=new ArrayList();
        String prevValue="";
        int j=0;
        for(int i=0;i<mainKeyList.size();i++){
            if(last){
                //symbolTable.put("IT",prevValue);
                datatypeTable.put("IT", "YARN_LITERAL");

                st.clear();
                break;
            }

            if(i==0&&i==mainKeyList.size()-1){
                    checkToken("literal", "endline", "",lineCounter);//if nothing follows the keyword "SMOOSH" this error will output
                    return false;
                }
            else{

                if(i==mainKeyList.size()-1)last=true;//tells the function that this is the last word in the line
               
               // if(i!=0 && !mainValueList.get(i-1).equals("AN")) prevValue = mainValueList.get(i-1);
                
                if(!smooshGrammar(prevValue,mainKeyList.get(i),mainValueList.get(i).toString(),st,val,lineCounter,start,last, symbolTable, mainValueList)) return false;
                start=false;
            }
         }

       if(!st.empty()){
           errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;//if the stack is not empty, it means that the grammar is not correct
        }
        return st.empty();// returns true if stack is empty, else returns false then produces an error
    }
    /*****SMOOSH******/
    
    public boolean opGrammar(boolean start,String currKey,String currValue,Stack st,Pattern[] opArray, int lineCounter, Stack val,Hashtable symbolTable){
        boolean error=false;
   
        System.out.println("HEYY"+st);
        if(st.empty()&&!start) {//if it tries to push an invalid value when the stack is empty, the function will produce an error
            checkToken("endline", currKey, currValue,lineCounter);
            return false;
        }
        else if (isOperator(currValue,opArray)){//if the current value is operator
            //it will only be pushed if the previous value is either "AN", an operator or if it is the first value in the stack
            if(st.empty()||st.peek().equals("AN")||isOperator(st.peek().toString(),opArray)){
                st.push(currValue);
                val.push(currValue);
            }
            else if(isVar_Lit(st.peek().toString())) error=true;
        }
        
        else if(currValue.equals("AN")){
            if(st.empty() || isOperator(st.peek().toString(),opArray)) error=true;
            else if( isVar_Lit(st.peek().toString())){
                st.push(currValue);
                val.push(currValue);
            }
        }
    
        else if(isVar_Lit(currKey)){
           // if(currValue.equals("IT")){}
            if(st.empty()) System.out.println("Error");
            else if(isOperator(st.peek().toString(),opArray)){
                st.push(currKey);
                val.push(currValue);
            }
            else if(isVar_Lit(st.peek().toString())) error=true;
            else if(st.peek().equals("AN")){
               
                st.pop();
                String lastKey=st.pop().toString();
                st.pop();
                
                val.pop();
               
                String num=val.pop().toString();
                String op=val.pop().toString();
                
                if(currKey.equals("VARIDENT")){
                    if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(currValue).equals(true)){
                        symbolTable.put(currValue,""); 
                        datatypeTable.put(currValue,"NOOB");
                    }
                    if(!symbolTable.get(currValue).equals("")){
                        currValue=symbolTable.get(currValue).toString();
                    }else{
                        String e ="ERROR: Cannot implicitly cast NOOB to int since variable: "+currValue+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                                
                        }
                }
                
                   if(lastKey.equals("VARIDENT")){
                       if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(num).equals(true)){
                           symbolTable.put(currValue,"");
                           datatypeTable.put(currValue,"NOOB");
                       }
                    if(!symbolTable.get(num).equals("")){
                        num=symbolTable.get(num).toString();
                    }else{
                        String e ="ERROR: Cannot implicitly cast NOOB to int since variable: "+num+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                                
                        }
                }
                
                if(isInteger(currValue,lineCounter,currKey)&& isInteger(num,lineCounter,lastKey)){
                    
                    num=removeQuotes(num);
                    currValue=removeQuotes(currValue);
                    float valueFloat;
                    int valueInt;
                    //System.out.println("CUUR: "+currKey);
                    // System.out.println("NUM: "+lastKey);
                    Number value=calculate(parseNum(currValue,currKey),op,parseNum(num,lastKey));
                    
                    
                   
                    
                    if(!st.empty()){
                        if(value instanceof Float){
                            valueFloat=value.floatValue();
                            opGrammar(start,"YARN_LITERAL",Float.toString(valueFloat),st,opArray,lineCounter,val,symbolTable);
                        }
                        else if(value instanceof Integer){
                            valueInt =value.intValue(); 
                            opGrammar(start,"YARN_LITERAL",Integer.toString(valueInt),st,opArray,lineCounter,val,symbolTable);
                        }                     

                    }
                    
                    else{
                        
                        if(orlyFlag!=true&&wtfFlag!=true){
                        symbolTable.put("IT",value.toString());
                        if(value.toString().contains("."))datatypeTable.put("IT","NUMBAR_LITERAL");
                        else datatypeTable.put("IT","NUMBR_LITERAL");
                        }
                    }
                    
                }
               
               else return false;
            }

        }
        
        else error=true;
        
        if (error){
            if(isOperator(st.peek().toString(),opArray)){
                checkToken("variable, literal or operator", currKey, currValue,lineCounter);
            }

            else if(isVar_Lit(st.peek().toString()))
                checkToken("KEYWORD(AN)", currKey, currValue,lineCounter);
            
            else if(st.peek().equals("AN"))
                checkToken("operator, variable or literal", currKey, currValue,lineCounter);
            
            return false;
        
        }
       return true;     
    }
    
        
    public boolean declGrammar(String currKey,String currValue,Stack st, int lineCounter,boolean start, boolean last,Hashtable symbolTable,ArrayList<String> mainValueList){
            boolean error=false;
         //grammar for I HAS A
        if(!start && st.empty()){ 
            checkToken("endline", currKey, currValue,lineCounter);//if it tries to push an invalid value when the stack is empty, the function will produce an error
            return false;//returning false will automatically stop the whole program and output an error
        }
         
        if(currValue.equals("I HAS A") && start){//if the current value is I HAS A and is the beginning of the loop,
                
             if(st.empty())st.push(currValue);//it will be pushed into the stack
             else error=true;
         }
         
         else if(currKey.equals("VARIDENT")){ // if the current value is a variable, it  can only be pushed when...
             if(!st.empty()){
                if(st.peek().equals("I HAS A")&& last){
                        
                        st.pop();
                        if(orlyFlag!=true&&wtfFlag!=true){
                        symbolTable.put(currValue,"");
                        datatypeTable.put(currValue,"NOOB");
                        }//value is set to false since the value is not initialized
                         
                    }//if it is the last value in line, it will pop all elements in the stack because it is a valid grammar
                else if(st.peek().equals("I HAS A")&& !last)st.push(currKey);//...pushed when it is the not last item and the previous value is I HAS A 
                else if(st.peek().equals("ITZ")){//if the previous value is ITZ, it will pop three times because it means that we have I HAS A, VARIDENT and ITZ as previous values  
                        st.pop();// therefore, it is a valid grammar for declaration
                        st.pop();
                        st.pop();
                        if(!symbolTable.get(currValue).equals("")){//if current variable is not null
                            if(orlyFlag!=true&&wtfFlag!=true){
                            symbolTable.put(mainValueList.get(1),symbolTable.get(currValue));
                            datatypeTable.put(mainValueList.get(1), datatypeTable.get(currValue));
                            } //gets the value of the current variable then assigns it to the first variable
                       // System.out.println(symbolTable.get());
                        }
                        else{
                        String e ="ERROR: Cannot implicitly cast NOOB since variable: "+currValue+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                                
                        }
                }
                else error=true;
             }
         }
         
         else if(currValue.equals("ITZ")){
             if(st.peek().equals("VARIDENT"))st.push(currValue);//ITZ can only be pushed when the previous value is a variable
             else error=true;
         }
             
         
         else if (currKey.equals("NUMBR_LITERAL")||
                  currKey.equals("NUMBAR_LITERAL")||currKey.equals("YARN_LITERAL")||currKey.equals("TROOF_LITERAL")){
             if(st.peek().equals("ITZ")){//this case is the same with variable
             st.pop();
             st.pop();
             st.pop();
                if(orlyFlag!=true&&wtfFlag!=true){
                symbolTable.put(mainValueList.get(1),currValue);
                datatypeTable.put(mainValueList.get(1), currKey);
                }
                 
             }
             
             else error=true;
         } 
         
         else error=true;
         if(error){//if the current value is invalid, it will look for possible errors
             //System.out.println(st);
              
            if(st.peek().equals("I HAS A"))
                     checkToken("variable identifier", currKey, currValue,lineCounter);//these statements will produce an error depending on what lexeme is expected
             else if(st.peek().equals("VARIDENT"))
                    checkToken("KEYWORD(ITZ) or endline", currKey, currValue,lineCounter);
             else if(st.peek().equals("ITZ"))
                 checkToken("variable identifier or literal", currKey, currValue,lineCounter);
             return false;
         
         }
         
         return true;
     
     }
        
    public boolean opGrammarIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,Pattern[] opArray,int lineCounter,Hashtable symbolTable){
        Stack st = new Stack();
        Stack val = new Stack();
        boolean start=true;
        ArrayList<String> opKeyList =new ArrayList();
        ArrayList<String> opValueList=new ArrayList();
        for(int i=0;i<mainKeyList.size();i++){
            
             if(mainValueList.get(i).equals("BTW"))break;
             else if(mainValueList.get(i).equals("SMOOSH")){
                for(int j=i;j<mainKeyList.size();j++){
                opKeyList.add(mainKeyList.get(j));
                opValueList.add(mainValueList.get(j));
                } 
                if(!smooshGrammarIterator(opKeyList,opValueList,lineCounter,symbolTable,opArray)){
                   
                 return false;
                 }
                 if(!opGrammar(start,"YARN_LITERAL",removeQuotes(symbolTable.get("IT").toString()),st,opArray,lineCounter,val,symbolTable)) return false;
                    start=false;
                 
//        
//                 symbolTable.put("IT","");
//                 datatypeTable.put("IT", "NOOB");
                
                 break;
            }
             else{ if(!opGrammar(start,mainKeyList.get(i),mainValueList.get(i),st,opArray,lineCounter,val,symbolTable)) return false;
                    start=false;}
        }
               
        if(!st.empty()){
                    errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
                 }
        return st.empty();
    }

    public boolean declGrammarIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter,Hashtable symbolTable,Pattern[] opArray){
        Stack st = new Stack();//this function iterates over a given line in the code with the "I HAS A" as start variable
        boolean start=true;//then sends parameters to another function word by word for processing
        boolean last=false;
        ArrayList<String> opKeyList =new ArrayList();
        ArrayList<String> opValueList=new ArrayList();
        for(int i=0;i<mainKeyList.size();i++){
            if(last)break;
        if(i==0&&i==mainKeyList.size()-1){
                checkToken("identifier", "endline", "",lineCounter);//if nothing follows the keyword "I HAS A" this error will output
                return false;
            }
        else{
                 if(mainValueList.get(i).equals("BTW")){ //if BTW is encountered, the rest of the line is ignored
                    if(!st.empty())st.pop();
                    i-=1;//reverses the loop by 1 then tells the function that that lexeme is the last word
                    if(i==0)start=true;
                    last=true;
                }
                 else if(!st.empty()&&st.peek().equals("ITZ")&&isOperator(mainValueList.get(i),opArray)){//if value will be from an operation
                    for(int j=i;j<mainKeyList.size();j++){
                        opKeyList.add(mainKeyList.get(j));
                        opValueList.add(mainValueList.get(j));
                    } 
                     if(!opGrammarIterator(opKeyList,opValueList,opArray,lineCounter,symbolTable)){

                         return false;
                     }
                     st.clear();
                     if(orlyFlag!=true&&wtfFlag!=true){
                     symbolTable.put(mainValueList.get(1),symbolTable.get("IT"));
                     datatypeTable.put(mainValueList.get(1), datatypeTable.get("IT"));
                     symbolTable.put("IT","");
                     datatypeTable.put("IT", "NOOB");
                     }
                     break;
            }
            if(i==mainKeyList.size()-1)last=true;//tells the function that this is the last word in the line
            if(!declGrammar(mainKeyList.get(i),mainValueList.get(i),st,lineCounter,start,last, symbolTable, mainValueList)) return false;
            start=false;
        }
     }
       if(!st.empty()){
           errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;//if the stack is not empty, it means that the grammar is not correct
        }
        return st.empty();// returns true if stack is empty, else returns false then produces an error
    }



    public boolean gimmehGrammar(String currKey ,String currValue, Stack st, int lineCounter, boolean last,boolean start, Hashtable symbolTable){
       boolean error=false;
       //System.out.println(st);
       if(currValue.equals("GIMMEH")&& start){
           if(st.empty())
               st.push(currValue);
           else error=true;
       }

       else if(currKey.equals("VARIDENT")&& last){
            if(st.peek().equals("GIMMEH")){
                symbolTable.put(currValue,true);
                st.pop();
              if(orlyFlag!=true&&wtfFlag!=true){
                String newValue= JOptionPane.showInputDialog("Enter input for variable "+currValue+":  ");
        
               if(newValue!=null){ 
               
                System.out.println("Whatt??");
                symbolTable.put(currValue,"\""+newValue+"\"");
                datatypeTable.put(currValue, "YARN_LITERAL");
               }
               
               else{
                    checkToken("user input", "null", "",lineCounter);
                    return false;
               }
              }
            }
            else error=true;
       }

       else if((currKey.equals("VARIDENT")&& !last)){
       if(st.peek().equals("GIMMEH"))    
            st.push(currKey);
       else error=true;

       }
       else error=true;
       if(error){
           if(st.peek().equals("GIMMEH"))
               checkToken("identifier", currKey, currValue,lineCounter);
           if(st.peek().equals("VARIDENT"))
               checkToken("endline", currKey, currValue,lineCounter);
        return false;
       }
       
       return true;

    }

  public boolean gimmehGrammarIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter, Hashtable symbolTable){
     
    Stack st = new Stack();
    boolean start=true;
    boolean last = false;
    for(int i=0;i<mainKeyList.size();i++){
        if(last)break;
        if(i==0&&i==mainKeyList.size()-1){
            checkToken("identifier", "endline", "",lineCounter);
            return false;
        }
        
        else{
            if(mainValueList.get(i).equals("BTW")){
                    if(!st.empty())st.pop();
                    i-=1;
                    if(i==0)start=true;
                    last=true;
                }
            if(i==mainKeyList.size()-1)last=true;
            if(!gimmehGrammar(mainKeyList.get(i),mainValueList.get(i),st,lineCounter,last,start, symbolTable)) return false;
            start=false;
        }
    }
    if(!st.empty()){
           errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
        }
    
    return st.empty();
    }
  
   public boolean visibleGrammar(ArrayList<String> outputList,String currKey ,String currValue, Stack st, int lineCounter, boolean last,boolean start, Hashtable symbolTable,ArrayList<String> mainValueList){
        boolean error=false;
  
       if(currValue.equals("VISIBLE")&& start){
           if(st.empty())
               st.push(currValue);
           
           else error=true;
       }
       else if(!st.empty()&&isVar_Lit_Troof(currKey)){
                
            if(st.peek().equals("VISIBLE")){
                    if(last)st.pop();
                    if(currKey.equals("VARIDENT")){
                        if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(currValue).equals(true)){
                            symbolTable.put(currValue,"");  
                            datatypeTable.put(currValue, "NOOB");
                        }
                        if(symbolTable.get(currValue).equals("")){
                        String e ="Visible ERROR: Cannot implicitly cast NOOB since variable: "+currValue+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                        }
                       else {currValue=removeQuotes(symbolTable.get(currValue).toString());
                       if(orlyFlag!=true&&wtfFlag!=true){
                           outputTerminal.setText(outputTerminal.getText()+currValue);
                           if(last){
                               outputTerminal.setText(outputTerminal.getText()+"\n");
                             
                           }
                       }
                       outputList.add(currValue);}
                    }
                    else{
                        currValue=removeQuotes(currValue);
                        outputList.add(currValue);
                        if(orlyFlag!=true&&wtfFlag!=true){
                            outputTerminal.setText(outputTerminal.getText()+currValue);
                            if(last)outputTerminal.setText(outputTerminal.getText()+"\n");
                        }
                    }
                    
                }
                
            else error=true;
       }
       else if((isVar_Lit_Troof(currKey)&& !last)){
           
            st.push(currKey);
       }

       else error=true;
       if(!st.empty()&&error){
           if(st.peek().equals("VISIBLE"))
               checkToken("identifier or literal or expression", currKey, currValue,lineCounter);
               else if(isVar_Lit_Troof(st.peek().toString()))
               checkToken("endline", currKey, currValue,lineCounter);
       return false;
       }
       
       return true;

}
    
    public boolean visibleGrammarIterator(ArrayList<String> outputList,ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter,Pattern[] opArray, Hashtable symbolTable, Pattern[] boolArray){
        
        Stack st = new Stack();
        //boolean start=true;
        boolean last = false;
        ArrayList<String> opKeyList =new ArrayList();
        ArrayList<String> opValueList=new ArrayList();
        
        //Stack st = new Stack();
        Stack val = new Stack();
        Stack stPass = new Stack();
        Stack valPass =new Stack();
        Stack stPassBool = new Stack(); Stack stSmoosh = new Stack();
        Stack valPassBool =new Stack(); Stack valSmoosh = new Stack();
        boolean start=true;
        boolean startOp=false;boolean startSmoosh=false;
        boolean startBool=false;boolean passSmoosh=false;
        boolean pass = false;String prevValue="";
        boolean passBool = false;boolean lastSmoosh=false;
        int visibleCounter=0;
         for(int i=0;i<mainKeyList.size();i++){
         if(i==0&&i==mainKeyList.size()-1){
               if(orlyFlag!=true&&wtfFlag!=true){
                outputTerminal.setText(outputTerminal.getText()+"\n");
                outputList.add("\n");}
                return true;
            }
           
        if(mainValueList.get(i).equals("BTW")){if(orlyFlag!=true&&wtfFlag!=true)outputTerminal.setText(outputTerminal.getText()+"\n");st.pop();break;}
        
        if(mainValueList.get(i).equals("SMOOSH")&&pass!=true&&passBool!=true){
            startSmoosh=true;
            passSmoosh=true;
            System.out.println("HEYYYYYY");
        }
        if(passSmoosh){
            System.out.println("STPASS"+ stPass);

            if(i!=mainKeyList.size()-1&&!mainValueList.get(i).equals("AN")&&!mainValueList.get(i).equals("SMOOSH")){
                
                if(!mainValueList.get(i+1).equals("AN")){lastSmoosh=true;;System.out.println("1WHAT THE");}
            }else if(i==mainKeyList.size()-1){lastSmoosh=true;System.out.println("WHAT THE");}
            if(!smooshGrammar(prevValue,mainKeyList.get(i),mainValueList.get(i),stSmoosh,valSmoosh,lineCounter,startSmoosh,lastSmoosh, symbolTable, mainValueList)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n");return false;}
            startSmoosh=false;


            if(!startSmoosh && stSmoosh.isEmpty()){
                System.out.println("DAAN DITO"+symbolTable.get("IT").toString());
                if(i==mainKeyList.size()-1)last=true;
                if(!visibleGrammar(outputList,"YARN_LITERAL",symbolTable.get("IT").toString(),st,lineCounter,last,start,symbolTable,mainValueList)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n"); return false;}
                startSmoosh=false;
                lastSmoosh=false;
                passSmoosh=false;
                prevValue="";
                start=false;
                visibleCounter+=1;
                symbolTable.put("IT","");  
                datatypeTable.put("IT", "NOOB");
                continue;

            }
                
              
                
            }/////end of pass smoosh
        
         if(isBool(mainValueList.get(i),boolArray)&&pass!=true&&passSmoosh!=true){
            startBool=true;
            passBool=true;
            System.out.println("HEYYYYYY");
        }
        if(passBool){
            System.out.println("STPASS"+ stPass);


            if(!boolGrammar(startBool,mainKeyList.get(i),mainValueList.get(i),stPassBool,boolArray,lineCounter,valPassBool,symbolTable)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n");return false;}
            startBool=false;


            if(!startBool && stPassBool.isEmpty()){
                System.out.println("DAAN DITO"+symbolTable.get("IT").toString());
                if(i==mainKeyList.size()-1)last=true;
                if(!visibleGrammar(outputList,"YARN_LITERAL",symbolTable.get("IT").toString(),st,lineCounter,last,start,symbolTable,mainValueList)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n"); return false;}
                startBool=false;
                passBool=false;
                start=false;
                visibleCounter+=1;
                symbolTable.put("IT","");  
                datatypeTable.put("IT", "NOOB");
                continue;

            }
                
              
                
            }/////end of pass bool
        
        if(isOperator(mainValueList.get(i),opArray)&&passBool!=true&&passSmoosh!=true){
            startOp=true;
            pass=true;
        }
        if(pass){
            System.out.println("STPASS"+ stPass);


            if(!opGrammar(startOp,mainKeyList.get(i),mainValueList.get(i),stPass,opArray,lineCounter,valPass,symbolTable)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n");return false;}
            startOp=false;


            if(!startOp && stPass.isEmpty()){
                System.out.println("DAAN DITO"+symbolTable.get("IT").toString());
                if(i==mainKeyList.size()-1)last=true;
                if(!visibleGrammar(outputList,"YARN_LITERAL",symbolTable.get("IT").toString(),st,lineCounter,last,start,symbolTable,mainValueList)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n"); return false;}
                startOp=false;
                pass=false;
                start=false;
                symbolTable.put("IT","");  
                datatypeTable.put("IT", "NOOB");
                visibleCounter+=1;

            }
                
              
                
            }/////end of pass op
                    
        else if(!pass&&!passBool&&!passSmoosh){
            System.out.println("WHATTTT "+mainValueList.get(i));
            if(i==mainKeyList.size()-1)last=true;
            if(!visibleGrammar(outputList,mainKeyList.get(i),mainValueList.get(i),st,lineCounter,last,start,symbolTable,mainValueList)){if(visibleCounter>1)outputTerminal.setText(outputTerminal.getText()+"\n");return false;}
                start=false;
                visibleCounter+=1;
                    
         }
        }
        
        if(!st.empty()||!stPass.empty()||!stPassBool.empty()||!stSmoosh.empty()){
           if(!last)outputTerminal.setText(outputTerminal.getText()+"\n");
           errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
        }
        return st.empty()&&stPass.empty()&&stPassBool.empty()&&stSmoosh.empty();
    }
  
    public boolean varGrammar(ArrayList<String> outputList,String currKey ,String currValue, Stack st, int lineCounter, boolean last,boolean start, Hashtable symbolTable,ArrayList<String> mainValueList){
	    	boolean error=false;
	     
	    if(st.empty()&&!start){
	        checkToken("endline", currKey, currValue,lineCounter);
                System.out.println("WHAAAT");
	        return false;
	    }
	     
	    if(currKey.equals("VARIDENT") && start){
	         if(st.empty())st.push(currKey);
	         else error=true;
	     }
	     
	     else if(currKey.equals("VARIDENT")){
	         if(!st.empty()){ 
	            if(st.peek().equals("R")){
                        st.pop();
                        st.pop();
                        if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(currValue).equals(true)){
                            symbolTable.put(currValue,"");  
                            datatypeTable.put(currValue, "NOOB");
                        }
                        if(symbolTable.get(currValue).equals("")){
                            String e ="Visible ERROR: Cannot implicitly cast NOOB since variable: "+currValue+" is null at line "+lineCounter;
                            errorMsg=e;
                            return false;
                        }
                        else{
                            if(orlyFlag!=true&&wtfFlag!=true){
                            symbolTable.put(mainValueList.get(0),symbolTable.get(currValue));
                            datatypeTable.put(mainValueList.get(0), datatypeTable.get(currValue));
                            }
                        }
	            }
	            else error=true;
	         }
	     }
	     
	     else if(currValue.equals("R")){
	         if(st.peek().equals("VARIDENT"))st.push(currValue);
	         else error=true;
	     }
	     
	     else if (currKey.equals("NUMBR_LITERAL")||
	              currKey.equals("NUMBAR_LITERAL")||currKey.equals("YARN_LITERAL")||currKey.equals("TROOF_LITERAL")){
	         if(!mainValueList.isEmpty()&&st.peek().equals("R")){
	         st.pop();
	         st.pop();
                 if(orlyFlag!=true&&wtfFlag!=true){
                 symbolTable.put(mainValueList.get(0),currValue);}
                 datatypeTable.put(mainValueList.get(0), currKey);
	         }
	         
	         else error=true;
	     } 
	     
	     else error=true;
	     if(error){
	         //System.out.println(st);
	          
	        if(st.peek().equals("VARIDENT"))
	                 checkToken("KEYWORD(R) or endline", currKey, currValue,lineCounter);

	         else if(st.peek().equals("R"))
	             checkToken("variable identifier, literal or expression", currKey, currValue,lineCounter);
	         return false;
	     
	     }
	     
	     return true;
	 
	 }
  
     public boolean varidentGrammarIterator(ArrayList<String> outputList,ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter, Hashtable symbolTable,Pattern[] opArray,Pattern[] boolArray){
    
        Stack st = new Stack();
        boolean start=true;
        boolean last = false;
        ArrayList<String> opKeyList =new ArrayList();
        ArrayList<String> opValueList=new ArrayList();
        
        for(int i=0;i<mainKeyList.size();i++){
       
            if(last)break;
            else if(i==0&&i==mainKeyList.size()-1){
                if(orlyFlag!=true&&wtfFlag!=true){
                symbolTable.put("IT",(String)symbolTable.get(mainValueList.get(0)));
                datatypeTable.put("IT", datatypeTable.get(mainValueList.get(0)));
                }
                return true;
            }
           
            else if(!st.empty()&&st.peek().equals("R")&&isOperator(mainValueList.get(i),opArray)){
                for(int j=i;j<mainKeyList.size();j++){
                opKeyList.add(mainKeyList.get(j));
                opValueList.add(mainValueList.get(j));
                } 
                 if(!opGrammarIterator(opKeyList,opValueList,opArray,lineCounter,symbolTable)){
                   
                 return false;
                 }
                 st.clear();
                 if(orlyFlag!=true&&wtfFlag!=true){
                 symbolTable.put(mainValueList.get(0),symbolTable.get("IT").toString());
                 datatypeTable.put(mainValueList.get(0), datatypeTable.get("IT").toString());
                 symbolTable.put("IT","");
                 datatypeTable.put("IT", "NOOB");
                 
                 }
                 break;
            }
               else if(!st.empty()&&st.peek().equals("R")&&isBool(mainValueList.get(i),boolArray)){
                for(int j=i;j<mainKeyList.size();j++){
                opKeyList.add(mainKeyList.get(j));
                opValueList.add(mainValueList.get(j));
                } 
                if(!boolGrammarIterator(opKeyList,opValueList,boolArray,opArray,lineCounter,symbolTable)){
                   
                 return false;
                 }
                 st.clear();
                 if(orlyFlag!=true&&wtfFlag!=true){
                 symbolTable.put(mainValueList.get(0),symbolTable.get("IT").toString());
                 datatypeTable.put(mainValueList.get(0), datatypeTable.get("IT").toString());
                 symbolTable.put("IT","");
                 datatypeTable.put("IT", "NOOB");
                 }
                 break;
            }
            else if(!st.empty()&&st.peek().equals("R")&&(mainValueList.get(i).equals("ALL OF")||mainValueList.get(i).equals("ANY OF"))){
                for(int j=i;j<mainKeyList.size();j++){
                opKeyList.add(mainKeyList.get(j));
                opValueList.add(mainValueList.get(j));
                } 
                if(!allAnyOfGrammarIterator(opKeyList,opValueList,boolArray,lineCounter,symbolTable)){
                   
                 return false;
                 }
                 st.clear();
                 if(orlyFlag!=true&&wtfFlag!=true){
                 symbolTable.put(mainValueList.get(0),symbolTable.get("IT").toString());
                 datatypeTable.put(mainValueList.get(0), datatypeTable.get("IT").toString());
                 symbolTable.put("IT","");
                 datatypeTable.put("IT", "NOOB");
                 }
                 break;
            }
             else if(!st.empty()&&st.peek().equals("R")&&mainValueList.get(i).equals("SMOOSH")){
                for(int j=i;j<mainKeyList.size();j++){
                opKeyList.add(mainKeyList.get(j));
                opValueList.add(mainValueList.get(j));
                } 
                if(!smooshGrammarIterator(opKeyList,opValueList,lineCounter,symbolTable,opArray)){
                   
                 return false;
                 }
                 st.clear();
                 if(orlyFlag!=true&&wtfFlag!=true){
                 symbolTable.put(mainValueList.get(0),symbolTable.get("IT").toString());
                 datatypeTable.put(mainValueList.get(0), datatypeTable.get("IT").toString());
                 symbolTable.put("IT","");
                 datatypeTable.put("IT", "NOOB");
                 }
                 break;
            }
            else{
                 if(mainValueList.get(i).equals("BTW")){
                    
                    if(!st.empty())st.pop();
                    else break;
                    i-=1;
                    if(i==0)start=true;
                    last=true;
                    
                }
                if(i==mainKeyList.size()-1)last=true;
                if(!varGrammar(outputList,mainKeyList.get(i),mainValueList.get(i),st,lineCounter,last,start,symbolTable,mainValueList)) return false;
                start=false;
            }
        }
        if(!st.empty()){
           errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
        }
        return st.empty();
    }
     
    
     /************* Boolean operations ****/
     public boolean calculateBool(String num, String op, String curr, String currKey, String lastKey, int lineCounter){
         boolean numVar=false;
         boolean currVar=false;
         System.out.println("num:: "+num);
         System.out.println("curr:: "+curr);
         if(!op.equals("BOTH SAEM")&&!op.equals("DIFFRINT")&& ((curr.equals("WIN")||curr.equals("FAIL"))&&(num.equals("FAIL")||num.equals("WIN")))){
             System.out.println("WHAT");
             if(num.equals("WIN"))numVar=true;
             else if(num.equals("FAIL"))numVar=false;

             if(curr.equals("WIN"))currVar=true;
             else if(curr.equals("FAIL"))currVar=false;

             switch(op){

                 case "BOTH OF":
                     return (numVar && currVar);
                 case "EITHER OF":
                      return (numVar || currVar);
                 case "WON OF":
                        return(numVar ^ currVar);

             }
         }
         else if(op.equals("BOTH SAEM")||op.equals("DIFFRINT")){
            switch(op){
                case "BOTH SAEM":
                    if((curr).equals(num)) return true;
                    else return false;
                case "DIFFRINT":
                    if((curr).equals(num))return false;
                    else return true;
                }
        }
     
        else{
             errorMsg="Syntax ERROR: Cannot parse literal at line "+lineCounter;
             if(!curr.equals("WIN")&&!curr.equals("FAIL"))checkToken("variable or troof literal", "", curr,lineCounter);
             else if(!num.equals("WIN")&&!num.equals("FAIL"))checkToken("variable or troof literal", "", num,lineCounter);
             errorFlag=true;
         }
         return false;
     }
     
    public boolean boolGrammar(boolean start,String currKey,String currValue,Stack st,Pattern[] boolArray, int lineCounter, Stack val,Hashtable symbolTable){
        boolean error=false;
        System.out.println("HEREEEE: "+val);
        System.out.println("HEREEEEST: "+st);
        //System.out.println(st);
        if(st.empty()&&!start) {//if it tries to push an invalid value when the stack is empty, the function will produce an error
            checkToken("endline", currKey, currValue,lineCounter);
            return false;
        }
//        else if(currValue.equals("NOT")){
//            if(st.empty()||st.peek().equals("AN")||isBool(st.peek().toString(),boolArray)){
//                st.push(currValue);
//                val.push(currValue);
//            }
            
        
        
       // }
        else if (isBool(currValue,boolArray)){//if the current value is boolean operator
            //it will only be pushed if the previous value is either "AN", an operator or if it is the first value in the stack
            if(st.empty()||st.peek().equals("AN")||isBool(st.peek().toString(),boolArray)){
                st.push(currValue);
                val.push(currValue);
            }
            else if(isVar_Troof(st.peek().toString())) error=true;
        }
        
        else if(currValue.equals("AN")){
            if(st.empty() || isBool(st.peek().toString(),boolArray)) error=true;
            else if( isVar_Lit_Troof(st.peek().toString())){
                st.push(currValue);
                val.push(currValue);
            }
        }
    
        else if(isVar_Lit_Troof(currKey)){
           // if(currValue.equals("IT")){}
            if(st.empty()) return false;
          
            else if(st.peek().equals("NOT")){
                String notKey=st.pop().toString();
                val.pop();
                 if(currKey.equals("VARIDENT")){
                     if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(currValue).equals(true)){
                         symbolTable.put(currValue,"");  
                         datatypeTable.put(currValue, "NOOB");
                     }
                    if(!symbolTable.get(currValue).equals("")){
                        currValue=symbolTable.get(currValue).toString();
                    }else{
                        String e ="ERROR: Cannot implicitly cast NOOB to TROOF since variable: "+currValue+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                                
                        }
                }
                
                 if(!currValue.equals("WIN")&&!currValue.equals("FAIL")){
                     checkToken("variable or troof literal","", currValue,lineCounter);
                     return false;
                    }
                if(!st.empty()){
                   if(currValue.equals("WIN"))boolGrammar(start,"TROOF_LITERAL","FAIL",st,boolArray,lineCounter,val,symbolTable);
                   else boolGrammar(start,"TROOF_LITERAL","WIN",st,boolArray,lineCounter,val,symbolTable);
                }
                else{
                    if(currValue.equals("WIN")){
                        if(orlyFlag!=true&&wtfFlag!=true){
                        symbolTable.put("IT", "FAIL");
                        datatypeTable.put("IT", "TROOF_LITERAL");
                        }
                    }
                    else if(currValue.equals("FAIL")){
                        if(orlyFlag!=true&&wtfFlag!=true){
                        symbolTable.put("IT", "WIN");
                        datatypeTable.put("IT", "TROOF_LITERAL");
                        }
                    }
                
                }
            }
            else if(isBool(st.peek().toString(),boolArray)){
                st.push(currKey);
                val.push(currValue);
            }
            else if(isVar_Lit_Troof(st.peek().toString())) error=true;
          
            else if(st.peek().equals("AN")){
               
                st.pop();
                String lastKey=st.pop().toString();
                st.pop();
                
                val.pop();
               
                String num=val.pop().toString();
                String op=val.pop().toString();
                
                if(currKey.equals("VARIDENT")){
                    if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(currValue).equals(true)){
                        symbolTable.put(currValue,""); 
                        datatypeTable.put(currValue, "NOOB");
                    }
                    if(!symbolTable.get(currValue).equals("")){
                        currValue=symbolTable.get(currValue).toString();
                    }else{
                        String e ="ERROR: Cannot implicitly cast NOOB since variable: "+currValue+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                                
                        }
                }
                
                   if(lastKey.equals("VARIDENT")){
                       if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(num).equals(true)){
                        symbolTable.put(currValue,""); 
                        datatypeTable.put(currValue, "NOOB");
                             }
                        if(!symbolTable.get(num).equals("")){
                            num=symbolTable.get(num).toString();
                        }else{
                            String e ="ERROR: Cannot implicitly cast NOOB since variable: "+num+" is null at line "+lineCounter;
                            errorMsg=e;
                            return false;

                            }
                     }
                   
                
               
                    
                boolean value=calculateBool(num,op,currValue,currKey,lastKey,lineCounter);
                if(errorFlag)return false;

                if(!st.empty()){
                   if(value) boolGrammar(start,"TROOF_LITERAL","WIN",st,boolArray,lineCounter,val,symbolTable);
                   else boolGrammar(start,"TROOF_LITERAL","FAIL",st,boolArray,lineCounter,val,symbolTable);

                }

                else{
                    if(value){
                        if(orlyFlag!=true&&wtfFlag!=true){
                        symbolTable.put("IT", "WIN");
                        datatypeTable.put("IT", "TROOF_LITERAL");
                        }
                    }
                    else{
                        if(orlyFlag!=true&&wtfFlag!=true){
                        symbolTable.put("IT", "FAIL");
                        datatypeTable.put("IT", "TROOF_LITERAL");
                        }
                    }
                }
                
               
            }//HERE

        }
        
        else error=true;
        
        if (error){
            errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
            if(!st.isEmpty()){
                
                if(isOperator(st.peek().toString(),boolArray)){
                    checkToken("variable or troof literal", currKey, currValue,lineCounter);
                }

                else if(isVar_Troof(st.peek().toString()))
                    checkToken("KEYWORD(AN)", currKey, currValue,lineCounter);

                else if(st.peek().equals("AN"))
                    checkToken("boolean operator, variable or TROOF literal", currKey, currValue,lineCounter);
            }
            return false;
        
        }
       return true;     
    }
     
  public boolean boolGrammarIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,Pattern[] boolArray,Pattern[] opArray,int lineCounter,Hashtable symbolTable){
        Stack st = new Stack();
        Stack val = new Stack();
        Stack stPass = new Stack();
        Stack valPass =new Stack();
        boolean start=true;
        boolean startOp=false;
        boolean pass = false;
        
        for(int i=0;i<mainKeyList.size();i++){
        if(mainValueList.get(i).equals("BTW"))break;

        if(isOperator(mainValueList.get(i),opArray)){
            startOp=true;
            pass=true;
        }
            if(pass){
                System.out.println("STPASS"+ stPass);
                
                  
                if(!opGrammar(startOp,mainKeyList.get(i),mainValueList.get(i),stPass,opArray,lineCounter,valPass,symbolTable)) return false;
                startOp=false;
                    
                
                if(!startOp && stPass.isEmpty()){
                    System.out.println("DAAN DITO"+symbolTable.get("IT").toString());
                    if(!boolGrammar(start,"YARN_LITERAL",symbolTable.get("IT").toString(),st,boolArray,lineCounter,val,symbolTable)) return false;
                    startOp=false;
                    pass=false;
                    start=false;
                    
                }
                
              
                
            }/////end of pass
                    
        else if(!pass){    
            if(!boolGrammar(start,mainKeyList.get(i),mainValueList.get(i),st,boolArray,lineCounter,val,symbolTable)) return false;
                    start=false;
                    
         }
        }
        System.out.println("HERE: "+val);
        if(!st.empty()||!stPass.empty()){
                    errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
                 }
        return st.empty() && stPass.empty();
    }
  
      public boolean allAnyOfGrammar(String oper,boolean start,String currKey,String currValue,Stack st,Pattern[] boolArray, int lineCounter, Stack val,Hashtable symbolTable){
        boolean error=false;
         System.out.println("At any of");
         System.out.println("val"+val);
   
        //System.out.println(st);
        if(st.empty()&&!start) {//if it tries to push an invalid value when the stack is empty, the function will produce an error
            checkToken("endline", currKey, currValue,lineCounter);
            return false;
        }

        else if (currValue.equals("ANY OF")||currValue.equals("ALL OF")){//if the current value is boolean operator
            //it will only be pushed if the previous value is either "AN", an operator or if it is the first value in the stack
            if(st.empty()){
                st.push(currValue);
                val.push(currValue);
            }
            else if(isVar_Troof(st.peek().toString())) error=true;
            else error=true;
        }
        
        else if(currValue.equals("AN")){
            if(st.empty()) error=true;
            else if( isVar_Troof(st.peek().toString())){
                st.push(currValue);
                val.push(currValue);
                 System.out.println("FVCK");
                
            }
        }
        
    
        else if(isVar_Troof(currKey)){
           // if(currValue.equals("IT")){}
           System.out.println("????");
            if(st.empty()) return false;

            else if(st.peek().equals("ANY OF")||st.peek().equals("ALL OF")||(st.peek().equals("AN"))){
                st.push(currKey);
                val.push(currValue);
            }
            else if(isVar_Troof(st.peek().toString())) error=true;
            else error=true;
          
        }
        
        else if(currValue.equals("MKAY")){
            boolean value=false;
            boolean tempVal;
          if(isVar_Troof(st.peek().toString())){     
            String temp=val.pop().toString();
            String tempKey=st.pop().toString();
                if(tempKey.equals("VARIDENT")){
                    if(!symbolTable.get(temp).equals("")){
                        temp=symbolTable.get(temp).toString();
                    }else{
                        String e ="ERROR: Cannot implicitly cast NOOB to TROOF since variable: "+currValue+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                    }
                 }
                if(temp.equals("WIN")) tempVal=true;
                else tempVal=false;
                
                
            while(!st.empty()&&!val.empty()){
                boolean numVal;
                String num=val.pop().toString(); 
                String lastKey=st.pop().toString();
                if(!isVar_Troof(lastKey))continue;
                
                             
               if(lastKey.equals("VARIDENT")){
                   if((wtfFlag==false&&orlyFlag==false)&&symbolTable.get(num).equals(true)){
                           symbolTable.put(currValue,"");
                           datatypeTable.put(currValue,"NOOB");
                       }
                    if(!symbolTable.get(num).equals("")){
                        num=symbolTable.get(num).toString();
                    }else{
                        String e ="ERROR: Cannot implicitly cast NOOB since variable: "+num+" is null at line "+lineCounter;
                        errorMsg=e;
                        return false;
                    }
                 }
               System.out.println("num key: "+lastKey);
               if(num.equals("WIN")) numVal=true;
               else numVal=false;
               
               System.out.println("num val: "+numVal+" tempVal: "+tempVal);
               if(oper.equals("ALL OF"))tempVal = numVal && tempVal;
               else if(oper.equals("ANY OF"))tempVal = numVal || tempVal;  
               System.out.println("tempVal "+tempVal);
            }
                st.clear();
                val.clear();
              if(tempVal){
                    if(orlyFlag!=true&&wtfFlag!=true){
                    symbolTable.put("IT", "WIN");
                    datatypeTable.put("IT", "TROOF_LITERAL");
                    }
                }
                else{
                    if(orlyFlag!=true&&wtfFlag!=true){
                    symbolTable.put("IT", "FAIL");
                    datatypeTable.put("IT", "TROOF_LITERAL");
                    }

                }
            }
          else error=true;
        }//HERE
        
        else error=true;
     
        if (!st.empty()&&error){
            
            if(st.peek().toString().equals("ALL OF")||st.peek().toString().equals("ANY OF")){
                checkToken("variable or troof literal", currKey, currValue,lineCounter);
            }

            else if(isVar_Troof(st.peek().toString()))
                checkToken("KEYWORD(AN) or KEYWORD(MKAY)", currKey, currValue,lineCounter);
            
            else if(st.peek().equals("AN"))
                checkToken("variable or TROOF literal", currKey, currValue,lineCounter);
            
            return false;
        
        }
       return true;     
    }
  
   public boolean allAnyOfGrammarIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,Pattern[] boolArray,int lineCounter,Hashtable symbolTable){
        Stack st = new Stack();
        Stack val = new Stack();
        Stack stPass =new Stack();
        Stack valPass=new Stack();
        boolean start=true;
        boolean startBool=false;
        boolean pass=false;
        String op = mainValueList.get(0);
        for(int i=0;i<mainKeyList.size();i++){
            
            if(mainValueList.get(i).equals("BTW"))break;
            if(isBool(mainValueList.get(i),boolArray)){
                startBool=true;
                pass=true;
            }
            if(pass){
                System.out.println("STPASS"+ stPass);
                if(!startBool && stPass.isEmpty()){
                    
                    if(!allAnyOfGrammar(op,start,"TROOF_LITERAL",symbolTable.get("IT").toString(),st,boolArray,lineCounter,val,symbolTable)) return false;
                    startBool=false;
                    pass=false;
                    start=false;
                    
                }
                
                else{
                    if(!boolGrammar(startBool,mainKeyList.get(i),mainValueList.get(i),stPass,boolArray,lineCounter,valPass,symbolTable)) return false;
                    startBool=false;
                }
                
            }
            
            if(!pass){
                
                if(!allAnyOfGrammar(op,start,mainKeyList.get(i),mainValueList.get(i),st,boolArray,lineCounter,val,symbolTable)) return false;
                    start=false;
                    System.out.println("HERE2: "+st);
            }
        }
        
        
          
        if(!st.empty()||!stPass.empty()){
                    errorMsg= "Syntax ERROR: Could not parse literal at line "+lineCounter;
                    return false;
                 }
        return st.empty() && stPass.empty();
    }
   
   
   public boolean isIfThen(String curr, Pattern[] ifThenArray){
       
         for(int i=0;i<ifThenArray.length;i++)
               if(ifThenArray[i].matcher(curr).matches())return true;  
         return false;
   }
   
   public boolean ifThenGrammar(String currValue, int lineCounter, Stack orlySt, boolean startOrly){
     boolean error=false;
     if(currValue.equals("O RLY?")){
        if((orlySt.empty()&&startOrly)){
            orlySt.push(currValue);
        }
        else error=true;
     }
     else if(currValue.equals("YA RLY")){
         if(orlySt.peek().equals("O RLY?"))orlySt.push("YA RLY");
         else error=true;
     }
     else if(currValue.equals("NO WAI")){
         if (orlySt.peek().equals("YA RLY"))orlySt.push("NO WAI");
         else error=true;
     }
     else if(currValue.equals("OIC")){
          if (orlySt.peek().equals("YA RLY")){
              orlySt.pop();
              orlySt.pop();  
          }
          else if(orlySt.peek().equals("NO WAI")){
              orlySt.pop();
              orlySt.pop();
              orlySt.pop();
      
          }
          else error=true;
     }
     if(!orlySt.empty()&&error){
     if(orlySt.peek().equals("O RLY?"))
         checkToken("KEYWORD(YA RLY)","KEYWORD", currValue,lineCounter);
     else if(orlySt.peek().equals("YA RLY"))
         checkToken("KEYWORD(NO WAI) OR KEYWORD(OIC)","KEYWORD", currValue,lineCounter);
     if(orlySt.peek().equals("NO WAI?"))
         checkToken("KEYWORD(OIC)","KEYWORD", currValue,lineCounter);
     return false;
     }
     return true;
   }
          
   public void ifThenIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter,Hashtable symbolTable,Pattern[] ifThenArray){
    
    boolean last = false;
    for(int i=0;i<mainKeyList.size();i++){
      if (isIfThen(mainValueList.get(i),ifThenArray)){
        orlyValueList.add(mainValueList.get(i));
        
      }
     
        ifThenCodeBlockValue.add(mainValueList);
        ifThenCodeBlockKey.add(mainKeyList);
        break;
      
    }
    
     
    
  
//        System.out.println(orlyValueList);
//        System.out.println(ifThenCodeBlockValue);
//        System.out.println(ifThenCodeBlockKey);
        
   
   }
   
   
     public boolean wtfGrammar(String currValue, int lineCounter, Stack wtfSt, boolean startOrly){
     boolean error=false;
     if(currValue.equals("WTF?")){
        if((wtfSt.empty()&&startOrly)){
            wtfSt.push(currValue);
        }
        else error=true;
     }
     else if(currValue.equals("OMG")){
         if(wtfSt.peek().equals("WTF?")||wtfSt.peek().equals("OMG"))wtfSt.push("OMG");
         else error=true;
     }
   
      else if(currValue.equals("OMGWTF")){
         if (wtfSt.peek().equals("OMG"))wtfSt.push("OMGWTF");
         else error=true;
     }
     else if(currValue.equals("OIC")){
          if (wtfSt.peek().equals("OMGWTF")){
              wtfSt.clear();
             
          }
      
          else error=true;
     }
     if(!wtfSt.empty()&&error){
     if(wtfSt.peek().equals("WTF?"))
         checkToken("KEYWORD(OMG)","KEYWORD", currValue,lineCounter);
     else if(wtfSt.peek().equals("OMG"))
         checkToken("KEYWORD(OMG) OR KEYWORD(OMGWTF) or KEYWORD(GTFO)","KEYWORD", currValue,lineCounter);
     else if(wtfSt.peek().equals("OMGWTF"))
         checkToken("KEYWORD(OIC)","KEYWORD", currValue,lineCounter);
     return false;
     }
     return true;
   }
   
    public boolean isWtf(String curr, Pattern[] wtfArray){
       
         for(int i=0;i<wtfArray.length;i++)
               if(wtfArray[i].matcher(curr).matches())return true;  
         return false;
   }
   
   public void wtfIterator(ArrayList<String> mainKeyList,ArrayList<String> mainValueList,int lineCounter,Hashtable symbolTable,Pattern[] wtfArray){
    
    boolean last = false;
    for(int i=0;i<mainKeyList.size();i++){
      if (isWtf(mainValueList.get(i),wtfArray)){
        wtfValueList.add(mainValueList.get(i));
        
      }
     
        wtfCodeBlockValue.add(mainValueList);
        wtfCodeBlockKey.add(mainKeyList);
        break;
      
    }
   }
   
   

ArrayList <String> orlyValueList= new ArrayList();
ArrayList<ArrayList<String>> ifThenCodeBlockValue = new ArrayList<>();
ArrayList<ArrayList<String>> ifThenCodeBlockKey = new ArrayList<>();
String tempIT;

ArrayList <String> wtfValueList= new ArrayList();
ArrayList<ArrayList<String>> wtfCodeBlockValue = new ArrayList<>();
ArrayList<ArrayList<String>> wtfCodeBlockKey = new ArrayList<>();
String wtfTempIT;
    
    public boolean syntaxAnalyzer(ArrayList<String> outputList,ArrayList<String> keyList, ArrayList<String> valueList, Pattern[] opArray, int lineCounter, Hashtable symbolTable, Pattern [] boolArray,Pattern [] ifThenArray, boolean endOfFile,Pattern [] wtfArray){
        System.out.println(keyList);
        System.out.println(valueList);
        Stack orlySt = new Stack();
        Stack wtfSt = new Stack();
        boolean startOrly=true; 
        boolean startWtf=true; 
        
       
            if(keyList.size()!=0){
                   ////// ORLY statement
                
                    if(valueList.get(0).equals("O RLY?")){
                        if(valueList.size()>1 &&!valueList.get(1).equals("BTW")){
                            errorMsg="Syntax ERROR: KEYWORD(O RLY?) cannot coexist with other statements";
                            return false;
                            }
                        if(!symbolTable.get("IT").equals("WIN")&&!symbolTable.get("IT").equals("FAIL")){
                            errorMsg="ERROR: Implicit variable IT should contain troof value; Got: IT = "+symbolTable.get("IT").toString()+" at line "+lineCounter;
                            return false;
                        }
                        else tempIT = symbolTable.get("IT").toString();
                        orlyFlag=true;   
                    }
                    if(orlyFlag==true){
                       
                        if(oicFlag==false)
                            ifThenIterator(keyList,valueList,lineCounter,symbolTable,ifThenArray);
                        else {
                           
                            orlyFlag=false;
                         
                        }
                    }
                    ////////end of O RLY??
                     ////// start of WTFF
                     if(valueList.get(0).equals("WTF?")){
                         if(valueList.size()>1 &&!valueList.get(1).equals("BTW")){
                            errorMsg="Syntax ERROR: KEYWORD(WTF?) cannot coexist with other statements";
                            return false;
                            }
                        if(symbolTable.get("IT").equals("")){
                            errorMsg="ERROR: Cannot implicitly cast NOOB since implicit variable: IT is null at line "+lineCounter;
                            return false;
                        }
                        else wtfTempIT = symbolTable.get("IT").toString();
                        wtfFlag=true;   
                    }
                    if(wtfFlag==true){
                       
                        if(oicWtfFlag==false)
                            wtfIterator(keyList,valueList,lineCounter,symbolTable,wtfArray);
                        else {
                            wtfFlag=false;
                        }
                    }
                     
                     
                     
                     ///// end of WTF
                    
                     if(valueList.get(0).equals("OIC")){
                         System.out.println("OICHEREEEE");
                        if(wtfFlag==false&&orlyFlag==false){
                          errorMsg= "Syntax ERROR: Invalid KEYWORD("+valueList.get(0)+") at the start of expression at line "+lineCounter;
                          return false;
                         }
                        
                        //WTF
                        if(wtfFlag){
                         oicWtfFlag=true;
                         wtfFlag=false;
                         for(int n=0; n<wtfValueList.size();n++){
                                if(!wtfGrammar(wtfValueList.get(n),lineCounter, wtfSt, startWtf))return false;
                                startWtf=false;
                            }
                            if(!wtfSt.empty()) {
                                errorMsg="Syntax Error: Cannot parse statement at line"+lineCounter;
                                return false;
                            }
                        
                        
                        if(!wtfCodeBlockValue.get(1).get(0).equals("OMG")){
                           checkToken("KEYWORD(OMG)", ifThenCodeBlockKey.get(1).get(0), ifThenCodeBlockValue.get(1).get(0),lineCounter);
                           return false;
                        }
                           String tempOmgVal="";
                           omgwtfFlag=true;
                           boolean startOmgWtf=false;
                           for(int b=0; b<wtfCodeBlockKey.size();b++){
                            if(wtfCodeBlockValue.get(b).get(0).equals("OIC")){omgFlag=false;omgwtfFlag=false;}
                            if(startOmgWtf){
                                if(omgwtfFlag){
                                    omgFlag=false;
                                    if(!syntaxAnalyzer(outputList,wtfCodeBlockKey.get(b),wtfCodeBlockValue.get(b),opArray, lineCounter,symbolTable, boolArray,ifThenArray,endOfFile,wtfArray)){
                                    errorFlag=true;
                                    return false;
                                    }
                                }
                            }

                            if(wtfCodeBlockValue.get(b).get(0).equals("OMGWTF")){
                                startOmgWtf=true;
                                if(wtfCodeBlockValue.get(b).size()>1){checkToken("endline",wtfCodeBlockKey.get(b).get(1),wtfCodeBlockValue.get(b).get(1),lineCounter);return false;}}
                                                        
                            if(wtfTempIT.equals(tempOmgVal)&&omgFlag==true){//do OMG
                                
                                if(wtfCodeBlockValue.get(b).get(0).equals("GTFO"))break;
                                else if(wtfCodeBlockValue.get(b).contains("GTFO")){errorMsg="Invalid expression containing KEYWORD(GTFO) at line "+lineCounter;return false;}
                                if(!syntaxAnalyzer(outputList,wtfCodeBlockKey.get(b),wtfCodeBlockValue.get(b),opArray, lineCounter,symbolTable, boolArray,ifThenArray,endOfFile,wtfArray)){
                                errorFlag=true;
                                return false;
                                }
                                //omgwtfFlag=false;
                            }
                            if(wtfCodeBlockValue.get(b).get(0).equals("OMG")){
                                omgFlag=true;
                                if(wtfCodeBlockKey.get(b).size()==1||(wtfCodeBlockKey.get(b).size()>2)||!isLit_Troof(wtfCodeBlockKey.get(b).get(1))){
                                    if(wtfCodeBlockKey.get(b).size()>2 && wtfCodeBlockValue.get(b).get(2).equals("BTW")){}
                                       
                                    else{
                                    errorMsg="ERROR: KEYWORD(OMG) should follow a single literal value at line "+lineCounter;
                                    return false;}
                                }
                                else tempOmgVal = wtfCodeBlockValue.get(b).get(1);
                            }
                        }
                            wtfTempIT="";
                            wtfCodeBlockValue.clear();
                            wtfCodeBlockKey.clear();
                            wtfValueList.clear();
                            wtfFlag=false;
                            omgFlag=false;
                            omgwtfFlag=false;
                            oicWtfFlag=false;
                            
                    }
                        //end of WTF
                        
                        //ORLY 
                        if(orlyFlag){
                            oicFlag=true;
                            orlyFlag=false;

                            for(int m=0; m<orlyValueList.size();m++){
                                if(!ifThenGrammar(orlyValueList.get(m),lineCounter, orlySt, startOrly))return false;
                                startOrly=false;
                            }
                            if(!orlySt.empty()) {
                                errorMsg="Syntax Error: Cannot parse statement at line"+lineCounter;
                                return false;
                            }

                               if(!ifThenCodeBlockValue.get(1).get(0).equals("YA RLY")){
                                   
                                   checkToken("KEYWORD(YA RLY)", ifThenCodeBlockKey.get(1).get(0), ifThenCodeBlockValue.get(1).get(0),lineCounter);
                                   return false;
                                }
                                for(int a=0; a<ifThenCodeBlockKey.size();a++){
                                    if(ifThenCodeBlockValue.get(a).get(0).equals("OIC")){
                                        if(ifThenCodeBlockValue.get(a).size()>1 &&!ifThenCodeBlockValue.get(a).get(1).equals("BTW") ){
                                            errorMsg="Syntax ERROR: KEYWORD(OIC) cannot coexist with other statements";
                                            return false;
                                        }
                                        nowaiFlag=false;yarlyFlag=false;
                                    }
                                    if(tempIT.equals("FAIL")&&nowaiFlag==true){//do NO WAI
                                        if(!syntaxAnalyzer(outputList,ifThenCodeBlockKey.get(a),ifThenCodeBlockValue.get(a),opArray, lineCounter,symbolTable, boolArray,ifThenArray,endOfFile,wtfArray)){
                                        errorFlag=true;
                                        return false;
                                        }
                                    }
                                    if(ifThenCodeBlockValue.get(a).get(0).equals("NO WAI")){
                                        if(ifThenCodeBlockValue.get(a).size()>1 &&!ifThenCodeBlockValue.get(a).get(1).equals("BTW") ){
                                            errorMsg="Syntax ERROR: KEYWORD(NO WAI) cannot coexist with other statements";
                                            return false;
                                        }
                                        nowaiFlag=true;yarlyFlag=false;
                                    }
                                    if(tempIT.equals("WIN")&&yarlyFlag==true){//do YA RLY
                                        if(!syntaxAnalyzer(outputList,ifThenCodeBlockKey.get(a),ifThenCodeBlockValue.get(a),opArray, lineCounter,symbolTable, boolArray,ifThenArray,endOfFile,wtfArray)){
                                        errorFlag=true;
                                        return false;
                                        }
                                    }
                                    if(ifThenCodeBlockValue.get(a).get(0).equals("YA RLY")){
                                        if(ifThenCodeBlockValue.get(a).size()>1 &&!ifThenCodeBlockValue.get(a).get(1).equals("BTW") ){
                                            errorMsg="Syntax ERROR: KEYWORD(YA RLY) cannot coexist with other statements";
                                            return false;
                                        }
                                        yarlyFlag=true;
                                    }
                                }
                            //reset
                            tempIT="";
                            ifThenCodeBlockValue.clear();
                            ifThenCodeBlockKey.clear();
                            orlyValueList.clear();
                            orlyFlag=false;
                            yarlyFlag=false;
                            nowaiFlag=false;
                            oicFlag=false;

                        }
                           
//                        if(symbolTable.containsValue(true)){
//                        String e ="Visible ERROR: Cannot implicitly cast NOOB since variable: "+currValue+" is null at line "+lineCounter;
//                        errorMsg=e;
//                        return false;}
                    return true;
                    }
                   
                     /////// end of OIC
                    
                    
                    for(int k=0;k<opArray.length;k++){//Operation grammar
                        if(opArray[k].matcher(valueList.get(0)).matches()){
                            if(!opGrammarIterator(keyList,valueList,opArray,lineCounter,symbolTable))return false;
                        }
                
                    }
                      for(int l=0; l<boolArray.length;l++){//Boolean operations
                       if(boolArray[l].matcher(valueList.get(0)).matches()){
                            if(!boolGrammarIterator(keyList,valueList,boolArray,opArray,lineCounter,symbolTable))return false;
                       }
                   }
                    //valid start variables
                   if(valueList.get(0).equals("I HAS A")){
                     if(!declGrammarIterator(keyList,valueList,lineCounter,symbolTable,opArray))return false;
                   }
                   
                   else if(valueList.get(0).equals("GIMMEH")){
                      if(!gimmehGrammarIterator(keyList,valueList,lineCounter,symbolTable))return false;
                   }
                   
                   else if(valueList.get(0).equals("VISIBLE")){
                      if(!visibleGrammarIterator(outputList,keyList,valueList,lineCounter,opArray,symbolTable,boolArray))return false;
                   }
                   else if(keyList.get(0).equals("VARIDENT")){
                       
                     if(!varidentGrammarIterator(outputList,keyList,valueList,lineCounter,symbolTable,opArray,boolArray))return false;
                                                                    
                   }
                    else if(valueList.get(0).equals("SMOOSH")){
                       
                     if(!smooshGrammarIterator(keyList,valueList,lineCounter,symbolTable,opArray))return false;
                                                                    
                   }
                   else if(valueList.get(0).equals("ALL OF")||valueList.get(0).equals("ANY OF")){
                       if(!allAnyOfGrammarIterator(keyList,valueList,boolArray,lineCounter,symbolTable))return false;
                   }
                    else if(isLit_Troof(keyList.get(0))){
                       if(keyList.size()>1){
                       errorMsg= "Syntax ERROR: Cannot parse literal at line "+lineCounter;
                       return false;
                       }
                       else if(!orlyFlag&&!wtfFlag){
                           symbolTable.put("IT",valueList.get(0));
                           datatypeTable.put("IT", valueList.get(0));
                       }
                   }
                 //invalid start variables
                    else if(wtfFlag==false&&oicWtfFlag==false){
                        System.out.println("?????! "+valueList);
                     if(valueList.get(0).equals("OMG")||valueList.get(0).equals("OMGWTF")||valueList.get(0).equals("GTFO")){
                         errorMsg= "Syntax ERROR: Invalid KEYWORD("+valueList.get(0)+") at the start of expression at line "+lineCounter;
                         return false;
                     }
                 }
                   if(orlyFlag==false&&oicFlag==false){
                      if(valueList.get(0).equals("YA RLY")||valueList.get(0).equals("NO WAI")){
                         errorMsg= "Syntax ERROR: Invalid KEYWORD("+valueList.get(0)+") at the start of expression at line "+lineCounter;
                         return false;
                     }
                 }
                 
                  if(valueList.get(0).equals("ITZ")||valueList.get(0).equals("R")||valueList.get(0).equals("MKAY")||valueList.get(0).equals("AN")){
                         errorMsg= "Syntax ERROR: Invalid KEYWORD("+valueList.get(0)+") at the start of expression at line "+lineCounter;
                         return false;
                     }   
                   
                   
                   
                
            }
            
            
            return true;
        
    }
    
    /***************** Lexical Analyzer *******/

    public boolean mainFunc( ArrayList<String> outputList,ArrayList<ArrayList<String>> mainKeyList, ArrayList<ArrayList<String>> mainValueList,String s,
        Pattern[] keywordArray,Pattern varident,Pattern[] literalArray, int lineCounter, boolean endOfFile,boolean[] has_hai_kthxbye, Pattern[] opArray, Hashtable symbolTable, Pattern [] boolArray,Pattern[] ifThenArray,Pattern [] wtfArray){
     
    ArrayList<String> keyList = new ArrayList<>();
    ArrayList<String> valueList = new ArrayList<>();
     String curr ="";
     String newString="";
     int counter=0;
     int strLength=0;
     /*flags*/
     boolean commentFlag=false;
     //boolean ifstartFlag=false;
    // boolean switchFlag=false;
    // boolean oicFlag=false;
    // boolean caseFlag=false;
     boolean isAfterKeyword = false;
     

     int lexemeCounter=0;
     char c;
        for (int i = 0; i < s.length(); i++){
            c = s.charAt(i);
            
            if(c!='\t')
            curr+=c;
            boolean isVariable=true;
            
            

            if(commentFlag){
                if(i==s.length()-1){
                    if(!errorFlag){
                        System.out.println("comment "+curr);
                        keyList.add("COMMENT");
                        valueList.add(curr.trim());
                        model.addRow(new String[]{curr.trim(), "COMMENT"});
                    }
                }
            }

            if(curr.length()!=0){
                if(curr.charAt(curr.length()-1)==' '|| i==s.length()-1|| curr.length()==s.length()){
                    /*OBTW*/
                    if("TLDR".equals(curr.trim())) {
                     if(obtwFlag!=true){

                     errorMsg= "Syntax ERROR: Invalid KEYWORD(TLDR) at the start of expression at line "+lineCounter;
                     return false;
                        }
                    }
                    if(obtwFlag){ 
                        //System.out.println("end? "+endOfFile);
                        if("TLDR".equals(curr.trim())) {
                          
                            
                            if(i!=s.length()-1){
                                String e ="Syntax ERROR: keyword(TLDR) cannot co-exist with other statements at line "+lineCounter;
                                //System.out.println(e);
                                errorMsg = e;
                                errorFlag=true;
                                 return false;
                            }
                            obtwFlag=false;
                            if(!errorFlag){
                                System.out.println("obtw comment "+obtwStr);
                                keyList.add("OBTW_COMMENT");
                                 valueList.add(obtwStr);
                                model.addRow(new String[]{obtwStr, "OBTW_COMMENT"});
                            }
                            obtwStr="";
                        }
                        else if((endOfFile && i==s.length()-1)||(endOfFile && curr.length()==s.length()) ){
                            String e = "Syntax ERROR: expected keyword(TLDR) as endline";
                            //System.out.println(e);
                            errorMsg = e;
                             return false;
                        }
                        else{
                            //curr+=c;
                            obtwStr=obtwStr.concat(curr);
                            curr="";
                            
                        }
                    }
                    /*OIC*/
//                    if(ifstartFlag){
//                        System.out.println("if");
//                         if("OIC".equals(curr.trim())) {
//                            if(i!=s.length()-1){
//                                String e = "Syntax ERROR: keyword(OIC) cannot co-exist with other statements at line "+lineCounter;
//                               // System.out.println(e);
//                                errorMsg = e;
//                                errorFlag=true;
//                                 return false;
//                            }
//                            else if(!endOfFile && i==s.length()-1){
//                                String e = "Syntax ERROR: expected keyword(OIC) as endline";
//                               // System.out.println(e);
//                                errorMsg = e;
//                                return false;
//                            }
//                        }
//                    }
            }
            if(commentFlag==false && obtwFlag==false){
                if(curr.length()!=0){
                    if(curr.charAt(curr.length()-1)==' '|| curr.length()==s.length()||i==s.length()-1){  

                        for(int j=0;j<keywordArray.length;j++){
                            if(keywordArray[j].matcher(curr.trim()).matches()){
                                 /*modified HAI*/
                                if("HAI".equals(curr.trim()))haiFlag=true;
                                
                                 System.out.println(haiFlag);
                                if(haiFlag!=true && (!"BTW".equals(curr.trim()) &&! "OBTW".equals(curr.trim()) && !"TLDR".equals(curr.trim()))){
                                    String e = "Syntax ERROR: KEYWORD(HAI) is required at the start of the program";
                                    errorMsg = e;
                                    return false;
                                }
                                 if(kthxFlag && (!"BTW".equals(curr.trim()) &&! "OBTW".equals(curr.trim()) && !"TLDR".equals(curr.trim()))){
                                    String e = "Syntax ERROR: KEYWORD(KTHXBYE) is required at the end of the program";
                                    errorMsg = e;
                                    return false;
                                }
                                 if("KTHXBYE".equals(curr.trim())){
                                  keyList.add("KEYWORD");
                                     valueList.add(curr.trim());
                                     model.addRow(new String[]{curr.trim(), "KEYWORD"});
                                    lexemeCounter+=1;  
                                 kthxFlag=true;
                                 if((orlyFlag==true&&oicFlag==false)||(wtfFlag==true&&oicWtfFlag==false)){
                                 checkToken("KEYWORD(OIC)","KEYWORD", curr.trim(),lineCounter);
                                 return false;
                                  }
                                    }
                                if((orlyFlag==true||wtfFlag==true)&&curr.trim().equals("I HAS A")){errorMsg="ERROR: Variable declarations must be done outside this program block at line "+lineCounter;return false;}
                                if (!errorFlag && (haiFlag==true)&& !kthxFlag||"BTW".equals(curr.trim()) || "OBTW".equals(curr.trim()) || "TLDR".equals(curr.trim())){
                                    System.out.println("keyword "+curr.trim());
                                     keyList.add("KEYWORD");
                                     valueList.add(curr.trim());
                                     model.addRow(new String[]{curr.trim(), "KEYWORD"});
                                    lexemeCounter+=1;  
                                }
                                /**/
                                /*syntax conditions*/
                                if("BTW".equals(curr.trim())) commentFlag=true;
                                else if("OBTW".equals(curr.trim())){
                                        /*if it is preceded by another string*/
                                    if(lexemeCounter>1) {
                                        String e = "Syntax ERROR: keyword(OBTW) cannot co-exist with other statements at line "+lineCounter;
                                        //System.out.println(e);
                                        errorMsg = e;
                                        errorFlag=true;
                                        obtwFlag=true;
                                        return false;
                                    }
                                    else obtwFlag=true;
                                }
//                                if("O RLY?".equals(curr.trim())) ifstartFlag=true;
//                                if("WTF?".equals(curr.trim())) switchFlag=true;
//                                if("OIC".equals(curr.trim())) oicFlag=true;
//                                if("OMG".equals(curr.trim())||"OMGWTF".equals(curr.trim())) caseFlag=true;
                                if("KTHXBYE".equals(curr.trim())) has_hai_kthxbye[1]=true;
                                curr="";
                                isVariable=false;
                            }
                        }
                    }
                }
                            
                if(isAfterKeyword == true && curr.length()!=0){
                    boolean checker=true;
                    if(!haiFlag){
                    String err="Syntax ERROR: KEYWORD(HAI) is required at the start of the program";
                    errorMsg=err;
                    return false;
                    }
                    if(kthxFlag){
                    String err="Syntax ERROR: KEYWORD(KTHXBYE) is already encountered";
                    errorMsg=err;
                    return false;
                    }
                    if(" ".equals(curr.substring(curr.length() - 1)) || curr.length()==s.length()){
                        for(int j=0;j<literalArray.length;j++){
                            if(literalArray[j].matcher(curr.trim()).matches()){
                                if (!errorFlag){
                                    if(j==0){
                                        System.out.println("numbr literal "+curr);
                                        keyList.add("NUMBR_LITERAL");
                                        valueList.add(curr.trim());
                                        model.addRow(new String[]{curr.trim(), "NUMBR_LITERAL"});
                                    }
                                    else if(j==1){
                                        System.out.println("numbar literal "+curr);
                                        keyList.add("NUMBAR_LITERAL");
                                        valueList.add(curr.trim());
                                        model.addRow(new String[]{curr.trim(), "NUMBAR_LITERAL"});
                                    }
                                    else if(j==2){
                                        System.out.println("troof literal "+curr);
                                        keyList.add("TROOF_LITERAL");
                                        valueList.add(curr.trim());
                                        model.addRow(new String[]{curr.trim(), "TROOF_LITERAL"});
                                    }
                                    else if(j==3){
                                        System.out.println("yarn literal "+curr);
                                        keyList.add("YARN_LITERAL");
                                        valueList.add(curr.trim());
                                        String fixedStr=removeQuotes(curr.trim());
                                        model.addRow(new String[]{"\"", "YARN_DELIMITER"});
                                        model.addRow(new String[]{fixedStr, "YARN_LITERAL"});
                                        model.addRow(new String[]{"\"", "YARN_DELIMITER"});
                                    }
                                    lexemeCounter++;
                                }
                                    curr="";
                                    isAfterKeyword=false;
                                    checker=false;
                            }           
                        }
                        
                        if (varident.matcher(curr.trim()).matches()&&curr.length()!=0) {
                                
                               if (!errorFlag){
                                   boolean varDeclared=false;
                                   for(int k=0;k<mainValueList.size();k++){
                                       if(mainValueList.get(k).contains(curr.trim()))
                                           if(!curr.trim().equals("IT"))varDeclared=true;
                                   }
                                   
                                   
                                   if(valueList.size()==0&&keyList.size()==0){
                                       if(varDeclared||curr.trim().equals("IT")){
                                        System.out.println("varident "+curr.trim());
                                        keyList.add("VARIDENT");
                                        valueList.add(curr.trim());
                                        model.addRow(new String[]{curr.trim(), "VARIDENT"});
                                        lexemeCounter++;
                                       }
                                       else{
                                            if(!curr.trim().equals("IT")){
                                                String e="Syntax ERROR: Reference to undeclared variable: "+curr.trim()+" at line "+lineCounter;
                                                errorMsg = e;
                                                errorFlag=true; 
                                                return false;
                                            }
                                       } 
                                      
                                   }
                                
                                   else if(valueList.get(valueList.size()-1).equals("I HAS A")||varDeclared){
                                        if(curr.trim().equals("IT")){
                                          String e="Syntax ERROR: Cannot declare the special variable IT at line "+lineCounter;
                                           errorMsg = e;
                                           errorFlag=true;
                                           return false;  
                                        }
                                        System.out.println("varident "+curr.trim());
                                        keyList.add("VARIDENT");
                                        valueList.add(curr.trim());
                                        model.addRow(new String[]{curr.trim(), "VARIDENT"});
                                        lexemeCounter++;
                                    }
                                   else{
                                          if(!curr.trim().equals("IT")){
                                                String e="Syntax ERROR: Reference to undeclared variable: "+curr.trim()+" at line "+lineCounter;
                                                errorMsg = e;
                                                errorFlag=true; 
                                                return false;
                                            }
                                          else{
                                        System.out.println("special varident "+curr.trim());
                                        keyList.add("VARIDENT");  //ignores the special variable IT
                                        valueList.add(curr.trim());
                                        model.addRow(new String[]{curr.trim(), "VARIDENT"});
                                        lexemeCounter++;
                                          
                                          }
                                   }
                               }
                               curr="";
                               isAfterKeyword = false;
                               checker=false;
                         }

                        if(checker){
                            if (curr.trim().length()==0){
                                  curr="";
                                  isAfterKeyword = false;
                                  checker=false;
                            }
                            else if(curr.charAt(0)=='"') {
                                if(i==s.length()-1 && curr.trim().substring(curr.trim().length() - 1)!="\""){
                                 String e="Syntax ERROR: Unrecognized sequence: "+curr+" at line "+lineCounter;
                                 errorMsg = e;
                                  errorFlag=true;
                                  curr="";
                                  isAfterKeyword = false;
                                  checker=false;
                                  return false;
                                }
                            }
                            else if(curr.length()!=0){
                                
                                String e="Syntax ERROR: Unrecognized sequence: "+curr+" at line "+lineCounter;
                                errorMsg = e;
                              
                                errorFlag=true;
                                curr="";
                                isAfterKeyword = false;
                                return false;
                            }
                        }
                    }    
                }
                        if(i==s.length()-1  && isVariable==true){
                            s=curr;
                            i=-1;
                            isAfterKeyword=true;
                            curr="";
                        }       
                }
            }
        }
        if(!errorFlag){
            mainKeyList.add(keyList); //main lists
            mainValueList.add(valueList);
            if(!syntaxAnalyzer(outputList,keyList,valueList,opArray, lineCounter,symbolTable, boolArray,ifThenArray,endOfFile,wtfArray)){
            errorFlag=true;
            return false;
            } //every line is passed to the syntax analyzer for checking
              //if there is an error, the function will return false and stop the program
        }
        return true;
    }
    
    private String literalMatch(String s){
        if (s.matches("^-?\\d+$"))        return "NUMBR";
        if (s.matches("^-?\\d*\\.\\d+$")) return "NUMBAR";
        if (s.matches("^\".*\"$"))        return "YARN";
        if (s.matches("(^WIN$)|(^FAIL$)"))return "TROOF";
        return "NOOB";
    }
    
      String errorMsg="";
    
    public void scanFile(File file) {
       
        //identifier
        Pattern varident = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$");
        Pattern funcident = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$");
        Pattern loopident = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$");
        //literal
        Pattern numbr = Pattern.compile("^-?\\d+$");
        Pattern numbar = Pattern.compile("^-?\\d*\\.\\d+$");
        Pattern yarn = Pattern.compile("^\".*\"$");
        Pattern troof = Pattern.compile("(^WIN$)|(^FAIL$)");
        //keywords
        Pattern hai = Pattern.compile("^HAI$");
        Pattern kthxbye = Pattern.compile("^KTHXBYE$");
        //comments
        Pattern btw = Pattern.compile("^BTW$");  
        Pattern obtw = Pattern.compile("^OBTW$");
        Pattern tldr = Pattern.compile("^TLDR$");
        //variable declaration
        Pattern ihasa = Pattern.compile("^I\\sHAS\\sA$");
        Pattern itz = Pattern.compile("^ITZ$");
        Pattern r = Pattern.compile("^R$");
        //operations
        Pattern summ = Pattern.compile("^SUM\\sOF$");
        Pattern subb = Pattern.compile("^DIFF\\sOF$");
        Pattern prod = Pattern.compile("^PRODUKT\\sOF$");
        Pattern divv = Pattern.compile("^QUOSHUNT\\sOF$");
        Pattern modd = Pattern.compile("^MOD\\sOF$");
        Pattern an = Pattern.compile("^AN$");
        
        //comparison
        Pattern bggr = Pattern.compile("^BIGGR\\sOF$");
        Pattern smllr = Pattern.compile("^SMALLR\\sOF$");
        
        Pattern bothsaem = Pattern.compile("^BOTH\\sSAEM$");
        Pattern diffrint = Pattern.compile("^DIFFRINT$");
        
        //boolean
        Pattern eitherof = Pattern.compile("^EITHER\\sOF$");
        Pattern wonof = Pattern.compile("^WON\\sOF");
        Pattern nott = Pattern.compile("^NOT$");
        Pattern anyof = Pattern.compile("^ANY\\sOF$");
        Pattern allof = Pattern.compile("^ALL\\sOF$"); 
        Pattern bothof = Pattern.compile("^BOTH\\sOF$");
        Pattern mkay = Pattern.compile("^MKAY$");

        //concatenate
        Pattern smoosh = Pattern.compile("^SMOOSH$");
        
        Pattern iz = Pattern.compile("^IZ$");
        //input/output
        Pattern visible = Pattern.compile("^VISIBLE$");
        Pattern gimmeh = Pattern.compile("^GIMMEH$");
        //if-then
        Pattern orly = Pattern.compile("^O\\sRLY\\?$");
        Pattern yarly = Pattern.compile("^YA\\sRLY$");
        Pattern mebbe = Pattern.compile("^MEBBE$");
        Pattern nowai = Pattern.compile("^NO\\sWAI$");
        Pattern gtfo = Pattern.compile("^GTFO$");
        //oic
        //switch-case
        Pattern oic = Pattern.compile("^OIC$");
        Pattern wtf = Pattern.compile("^WTF\\?$");
        Pattern omg = Pattern.compile("^OMG$");
        Pattern omgwtf = Pattern.compile("^OMGWTF$");

        //optional
        Pattern iminyr = Pattern.compile("^IM\\sIN\\sYR$");
        Pattern uppin = Pattern.compile("^UPPIN$");
        Pattern nerfin = Pattern.compile("^NERFIN$");
        Pattern yr = Pattern.compile("^YR$");
        Pattern til = Pattern.compile("^TIL$");
        Pattern wile = Pattern.compile("^WILE$");
        Pattern imouttayr = Pattern.compile("^IM\\sOUTTA\\sYR$");
        Pattern how = Pattern.compile("^HOW$");
         Pattern [] boolArray={eitherof,wonof,nott,bothof,bothsaem,diffrint};
        Pattern[] literalArray={numbr,numbar,troof,yarn};
        Pattern[] keywordArray={
            hai,kthxbye,btw,obtw,tldr,ihasa,itz,r,summ,subb,prod,divv,modd,bggr,
            smllr,bothof,eitherof,wonof,nott,anyof,allof,bothsaem,diffrint,smoosh,
            visible,gimmeh,orly,yarly,mebbe,nowai,gtfo,oic,wtf,omg,omgwtf,iminyr,uppin,nerfin, yr, til, wile, imouttayr,an, mkay,iz,how};
        Pattern[] opArray ={summ,subb,prod,divv,modd,bggr,smllr};
        Pattern[] ifThenArray={orly,yarly,nowai,oic};
        Pattern[] wtfArray={wtf,omg,omgwtf,oic};
           Hashtable symbolTable = new Hashtable();//data structure for symbol table
          

        Scanner sc2 = null;
        int lineCounter=0;
        ArrayList<String> outputList = new ArrayList<>();
        ArrayList<ArrayList<String>> mainKeyList = new ArrayList<>();
        ArrayList<ArrayList<String>> mainValueList = new ArrayList<>();
        boolean endOfFile = false;
        boolean[] has_hai_kthxbye = {false,false};
        if(orlyFlag!=true&&wtfFlag!=true){
        symbolTable.put("IT","");
        datatypeTable.put("IT", "NOOB");
        }
            try {
                sc2 = new Scanner(file);
                while (sc2.hasNextLine()) {
                    String s = sc2.nextLine();
                    while(s.equals("\n"))//skip empty line
                        s = sc2.nextLine();
                        lineCounter++;
                                if(!sc2.hasNext())endOfFile=true;
                        has_hai_kthxbye[0] = mainFunc(outputList,mainKeyList, mainValueList,s,keywordArray,varident, literalArray, lineCounter, endOfFile,has_hai_kthxbye,opArray,symbolTable,boolArray,ifThenArray,wtfArray);
                        if(has_hai_kthxbye[0]==false){
                                file=null;
                                outputTerminal.setText(outputTerminal.getText()+errorMsg+"\n");
                                System.out.println(errorMsg); //if error, the program will stop running and produce the errorMsg
                                errorFlag=false;
                                break;
                                }
                }
                    java.util.Enumeration<String> keyset = symbolTable.keys();
                    while(keyset.hasMoreElements()){
                        String key = keyset.nextElement();
                        String val = symbolTable.get(key).toString();
                        model1.addRow(
                            new String[]{
                                key, val, literalMatch(val)
                            }
                        );
                    }
                    System.out.println(symbolTable);
                    System.out.println(datatypeTable);
                    System.out.println(outputList);
                    
                    if(errorMsg.length()==0){
                        if(!outputList.isEmpty()){
                            System.out.println("\nOutput:");
                            for(int j=0;j<outputList.size();j++){
                                if("\n".equals(outputList.get(j)))System.out.print(outputList.get(j));
                                else{
                                    System.out.println(outputList.get(j));
                                }
                            }
                        }
                    }
                    
                //end of file
                /*KTHXBYE*/
                     if(has_hai_kthxbye[1]==false && has_hai_kthxbye[0]==true) {
                        errorFlag=true;
                        String e = "Syntax ERROR: KEYWORD(KTHXBYE) is required at the end of the program";
                        System.out.println(e);
                        errorMsg=e;
                            file = null;
                            outputTerminal.setText(outputTerminal.getText()+errorMsg+"\n");
                            System.out.println(errorMsg);
                        return;
                    }
            
            } catch (FileNotFoundException e) {
                e.printStackTrace();  
            }
    }
}