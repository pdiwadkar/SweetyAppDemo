/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

/**
 *
 * @author amruta
 */
public class Pair<T1,T2> {
    private T1 t1;
    private T2 t2;
    public Pair(T1 t1,T2 t2){
        this.t1 = t1;
        this.t2 = t2;
    }
    public T1 getFirst(){
        return t1;
    }
    public T2 getSecond(){
        return t2;
    }
    
    
}
