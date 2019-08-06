package javabyte.socket.test;

import kirchnersolutions.javabyte.driver.common.driver.DatabaseResults;

import java.util.Map;

class PrintResults {

    static void printResult(DatabaseResults results){
        if(results.isSuccess()){
            System.out.println("Request was successful");
            for(Object result: results.getResults()){
                String out = "";
                Map<String, String> res = (Map<String, String>)result;
                boolean first = true;
                for(String key : res.keySet()){
                    if(first){
                        out = key + " = " + res.get(key);
                        first = false;
                    }else{
                        out+= " : " + key + " = " + res.get(key);
                    }
                }
                System.out.println(out);
            }
        }else {
            System.out.println("Request was unsuccessful");
            System.out.println("Fail Message: " + results.getMessage());
        }
    }

}
