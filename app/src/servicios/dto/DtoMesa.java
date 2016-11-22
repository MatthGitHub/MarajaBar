/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import negocio.BarController;
import negocio.Mesa;

/**
 *
 * @author Matth
 */
public class DtoMesa {
    private Integer numeroMesa;

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    
    public DtoMesa cargarDto(Mesa mesaParaCargar)
    {
        try
        {
        this.setNumeroMesa(mesaParaCargar.getNumeroMesa());

        }catch(NullPointerException e)
        {
            throw  e;
        }
           //seguir hasta el final
        return this;
    }
}
