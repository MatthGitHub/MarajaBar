/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;



/**
 *
 * @author Matth
 */

public class Mesa {
    
    private int numeroMesa;
    private boolean estadoMesa;

    public Mesa(Integer numMesa){
        this.numeroMesa = numMesa;
    }
    
    
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public boolean isEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(boolean estadoMesa) {
        this.estadoMesa = estadoMesa;
    }
    
}
