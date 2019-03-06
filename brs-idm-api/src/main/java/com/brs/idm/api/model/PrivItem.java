package com.brs.idm.api.model;

import lombok.Data;

@Data
public class PrivItem{
        public String id;
        public String privId;
        public PrivItem(String id,String privId){
            this.id =id;
            this.privId =privId;
        }

    }