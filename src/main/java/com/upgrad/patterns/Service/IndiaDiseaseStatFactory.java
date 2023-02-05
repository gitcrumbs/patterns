package com.upgrad.patterns.Service;

import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import com.upgrad.patterns.Constants.SourceType;
import com.upgrad.patterns.Strategies.DiseaseShStrategy;
import com.upgrad.patterns.Strategies.JohnHopkinsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndiaDiseaseStatFactory {
    private IndianDiseaseStat diseaseShStrategy;
    private IndianDiseaseStat johnHopkinsStrategy;

    private IndianDiseaseStat requestedStrategy;

    @Autowired
    public IndiaDiseaseStatFactory(DiseaseShStrategy diseaseShStrategy, JohnHopkinsStrategy johnHopkinsStrategy)
    {
        this.diseaseShStrategy = diseaseShStrategy;
        this.johnHopkinsStrategy = johnHopkinsStrategy;
    }






    public IndianDiseaseStat GetInstance(SourceType sourceEnum) {


        //create a method named GetInstance with return type as IndianDiseaseStat and parameter of type sourceType
        //create a conditional statement
        //if the sourceType is JohnHopkins
        //return johnHopkinsStrategy
        //if the sourceType is DiseaseSh
        //return diseaseShStrategy

        //create a message for
        //throw the message as an Illegal argument exception

        if(sourceEnum.equals(SourceType.JohnHopkins)){
            this.requestedStrategy= johnHopkinsStrategy;
        }else if (sourceEnum.equals(SourceType.DiseaseSh)){
            this.requestedStrategy= diseaseShStrategy;
        }else{
            throw new IllegalArgumentException("invalid disease strategy/sourceType");
        }

        return requestedStrategy;
    }
}
