package com.emedinaa.androidvolleyphp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by emedinaa on 14/05/15.
 */
public class ResponseEntity  implements Serializable{

    private List<ItemEntity> data;
    /*
        {"data":[{"variable1":null,"variable2":null,"variableDeseableQueAparezca":null}]}
     */

    public List<ItemEntity> getData() {
        return data;
    }

    public void setData(List<ItemEntity> data) {
        this.data = data;
    }


    private class ItemEntity implements Serializable
    {
        private String variable1;
        private String variable2;
        private String variableDeseableQueAparezca;

        public String getVariable1() {
            return variable1;
        }

        public void setVariable1(String variable1) {
            this.variable1 = variable1;
        }

        public String getVariable2() {
            return variable2;
        }

        public void setVariable2(String variable2) {
            this.variable2 = variable2;
        }

        public String getVariableDeseableQueAparezca() {
            return variableDeseableQueAparezca;
        }

        public void setVariableDeseableQueAparezca(String variableDeseableQueAparezca) {
            this.variableDeseableQueAparezca = variableDeseableQueAparezca;
        }

        @Override
        public String toString() {
            return "ItemEntity{" +
                    "variable1='" + variable1 + '\'' +
                    ", variable2='" + variable2 + '\'' +
                    ", variableDeseableQueAparezca='" + variableDeseableQueAparezca + '\'' +
                    '}';
        }
    }
}
