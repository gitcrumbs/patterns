package com.upgrad.patterns.Service;

import com.upgrad.patterns.Constants.SourceType;
import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import com.upgrad.patterns.Strategies.DiseaseShStrategy;
import com.upgrad.patterns.Strategies.JohnHopkinsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseCountFacade {
    private final IndiaDiseaseStatFactory indiaDiseaseStat;

    //create a private object indiaDiseaseStat of type IndiaDiseaseStatFactory

    @Autowired
    public DiseaseCountFacade(IndiaDiseaseStatFactory indiaDiseaseStat)
    {
        this.indiaDiseaseStat = indiaDiseaseStat;
    }


    //create a public method getDiseaseShCount() that has Object as its return type
    //call the GetInstance method with DiseaseSh as the parameter using the indiaDiseaseStat object created on line 10
    //Based on the strategy returned, call the specific implementation of the GetActiveCount method
    //return the response
    public Object getDiseaseShCount() {

        System.out.println("Populate Disease SH count");
        IndianDiseaseStat value =indiaDiseaseStat.GetInstance(SourceType.DiseaseSh);
        return value.GetActiveCount();
    }

    //create a public method getJohnHopkinCount() that has Object as its return type
    //call the GetInstance method with JohnHopkins as the parameter using the indiaDiseaseStat object created on line 10
    //Based on the strategy returned, call the specific implementation of the GetActiveCount method
    //return the response
    public Object getJohnHopkinCount() {

        System.out.println("Populate Disease SH count");
        IndianDiseaseStat value = indiaDiseaseStat.GetInstance(SourceType.JohnHopkins);
        return value.GetActiveCount();
    }


    public Object getInfectedRatio(String sourceType) throws IllegalArgumentException {
        try {
            Float population = 900000000F;
            SourceType sourceEnum = SourceType.valueOf(sourceType);
            Float active = Float.valueOf(indiaDiseaseStat.GetInstance(sourceEnum).GetActiveCount());
            Float percent = Float.valueOf((active / population));
            return String.format("%.3f", percent * 100);
        }
        catch (Exception e) {
            String message = String.format("Invalid source type specified. Available source type (%s, %s)", SourceType.DiseaseSh, SourceType.JohnHopkins);
            throw new IllegalArgumentException(message);
        }
    }



}
