/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ge.fsmp.connector.engines;

/**
 * Implementers provide methods to convert views to domains and domains to views.
 *
 * @author joshuabridevaux
 *
 * @param <D> domain type
 * @param <V> view type
 */
public interface IDomainViewConverterEngine<D, V> {

    /**
     * Converts from the domain representation of an entity object to the view representation.
     *
     * @param domain
     * @return
     */
    V convertToView(D domain);

    /**
     * Converts from the view representation of an entity object to the domain representation.
     *
     * @param view
     * @return
     */
    D convertToDomain(V view);
}
