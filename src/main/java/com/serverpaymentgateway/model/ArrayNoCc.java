/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.serverpaymentgateway.model;

import java.util.ArrayList;

/**
 *
 * @author Arina Listyarini DA
 */
public class ArrayNoCc {
    private ArrayList<String> noCcs;

    public ArrayNoCc() {
        noCcs = new ArrayList<String>();
    }

    public ArrayList<String> getNoCcs() {
        return noCcs;
    }

    public void setNoCcs(ArrayList<String> noCcs) {
        this.noCcs = noCcs;
    }
}
