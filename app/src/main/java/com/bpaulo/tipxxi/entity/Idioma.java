package com.bpaulo.tipxxi.entity;

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Idioma {

    /*

       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaIdiomas.bas

            |--0 ----------------|--1 ----------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
            |Campo	 			 |Caption 		|Tipo 		   |Rodape	|Grid.largura  |Grid.alinhamento |Grid.tipo_letra  |Grid.tam_letra	 |Imprimir	 |Editavel|
            |--txt---------------|--txt---------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

            array_Campos = Array(_
                Array("IDIdioma",			"ID",		    T_ALFANUM,	False, 	3000, 	4, 				    "",				0, 				    False, 	    True), _
                Array("DesignacaoIdioma",   "Designação", 	T_ALFANUM, 	False, 	5950, 	1, 					"",				0, 					False, 		True) )

    */

    // TODO: Must be defined to create a new entity.
    private static final int NUMBER_OF_ATTRIBUTES = 2;

    // TODO: Must be defined to create a new entity.
    private static final boolean UNIQUE_REGISTER = false;

    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
    // Debugging: Adding "CAPTION" class variable.
    private static final String ENTITY_CAPTION = "Idioma";

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

    private static final boolean CREATE = true;
    private static final boolean READ = true;
    private static final boolean UPDATE = true;
    private static final boolean DELETE = true;

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

    private String idIdioma; // TODO: (Mandatory to create a new entity) Name pattern: 'id' + entity name.
    // private Integer idIdioma;
    private String nomeIdioma; // TODO: Name pattern: 'nome' + entity name.
    // private String designacaoIdioma;

    private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

    public Idioma() {

        super();
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public Idioma(String idIdioma, String nomeIdioma) {

        super();
        this.idIdioma = idIdioma;
        this.nomeIdioma = nomeIdioma;
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNomeIdioma() {
        return nomeIdioma;
    }

    public void setNomeIdioma(String nomeIdioma) {
        this.nomeIdioma = nomeIdioma;
    }

    public String[] getAttributeParameters(String pAttribute){

        String[] lAttributeParameters = new String[DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

        for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
            if (attributeParameters[i][0].equals(pAttribute)) {
                for (int j = 0; j < DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS; j++) {
                    lAttributeParameters[j] = attributeParameters[i][j];
                }
            }
        }

        return lAttributeParameters;

    }

    // TODO: Must be defined to create a new entity.
    private void setAttributeParameters() {

        attributeParameters[0][0] = "idIdioma"; // Campo: txt
        attributeParameters[0][1] = "ID"; // Caption: txt (grid/edit layout)
        attributeParameters[0][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        // attributeParameters[0][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[0][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[0][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[0][4] = "3000"; // Grid.largura: int (grid layout)
        attributeParameters[0][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[0][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[0][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[0][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[0][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[0][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[0][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[0][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[0][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[0][12] = "false"; // notNull: bln (validation)
        attributeParameters[0][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[0][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[0][15] = "0"; // ColIndex: int

        attributeParameters[1][0] = "nomeIdioma"; // Campo: txt
        attributeParameters[1][1] = "Designação"; // Caption: txt (grid/edit layout)
        attributeParameters[1][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[1][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[1][4] = "200"; // Grid.largura: int (grid layout)
        // attributeParameters[1][4] = "5950"; // Grid.largura: int (grid layout)
        attributeParameters[1][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[1][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[1][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[1][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[1][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[1][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[1][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[1][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[1][12] = "false"; // notNull: bln (validation)
        attributeParameters[1][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[1][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[1][15] = "1"; // ColIndex: int

    }

    @Override
    public String toString() {
        return "Idioma{" +
                "idIdioma=" + idIdioma +
                ", nomeIdioma='" + nomeIdioma + '\'' +
                ", attributeParameters=" + Arrays.toString(attributeParameters) +
                '}';
    }

}
