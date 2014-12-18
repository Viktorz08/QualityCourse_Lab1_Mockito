/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotmail.viktorz08.lab1b.appservices.query;

import java.util.concurrent.Callable;

public interface QueryExecutor<V, C extends QueryCommand> {
    V invoke(C cmd);
}


