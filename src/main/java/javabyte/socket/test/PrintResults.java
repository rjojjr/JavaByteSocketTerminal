package javabyte.socket.test;

import kirchnersolutions.javabyte.driver.common.driver.DatabaseResults;

import java.util.Map;

class PrintResults {

    static void printResult(DatabaseResults results){
        if(results.isSuccess()){
            System.out.println("Request was successful");
            for(Map<String, String> result : results.getResults()){
                String out = "";
                boolean first = true;
                for(String key : result.keySet()){
                    if(first){
                        out = key + " = " + result.get(key);
                        first = false;
                    }else{
                        out+= " : " + key + " = " + result.get(key);
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
