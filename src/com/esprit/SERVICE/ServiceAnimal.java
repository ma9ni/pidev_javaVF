/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.SERVICE;

import com.esprit.entities.Animal;
import java.util.ArrayList;

/**
 *
 * @author houssem
 */
public class ServiceAnimal {

    Animal animal = new Animal();

    public ArrayList<Animal> selectAll(int id) {
        return animal.selectAllFromMembre(id);
    }

}
