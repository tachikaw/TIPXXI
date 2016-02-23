package com.bpaulo.tipxxi.entity;

// TODO: Not used in TIPXXI.

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Tara {

    /*

       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaTaras.bas

	        |--0 -------------------|--1 -------------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
	        |Campo	 				|Caption 		  |Tipo 		 |Rodape  |Grid.largura	 |Grid.alinhamento |Grid.tipo_letra	 |Grid.tam_letra   |Imprimir   |Editavel|
	        |--txt------------------|--txt------------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

            array_Campos = Array(_
                Array("CodTara",		"Codigo",		T_ALFANUM,      False, 	3000, 		    4, 				"",				    0, 				False, 		True), _
                Array("DesignacaoTara",	"Designação",	T_ALFANUM, 		False, 	2500, 			1, 				"",					0, 				False, 		True), _
    			Array("PesoTara",		"Peso Tara",	T_DECIMAL, 	    False, 	3450, 			1, 				"",					0, 				False, 		True))

    */

    // TODO: Must be defined to create a new entity.
    private static final int NUMBER_OF_ATTRIBUTES = 3;

    // TODO: Must be defined to create a new entity.
    private static final boolean UNIQUE_REGISTER = false;

    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
    // Debugging: Adding "CAPTION" class variable.
    private static final String ENTITY_CAPTION = "Tara";

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

    private static final boolean CREATE = true;
    private static final boolean READ = true;
    private static final boolean UPDATE = true;
    private static final boolean DELETE = true;

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

    private String idTara; // TODO: (Mandatory to create a new entity) Name pattern: 'id' + entity name.
    // private Integer idTara;
    private String nomeTara; // TODO: Name pattern: 'nome' + entity name.
    // private String designacaoTara;
    private Float pesoTara;

    private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

    public Tara() {

        super();
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public Tara(String idTara, String nomeTara, Float pesoTara) {

        super();
        this.idTara = idTara;
        this.nomeTara = nomeTara;
        this.pesoTara = pesoTara;
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public String getIdTara() {
        return idTara;
    }

    public void setIdTara(String idTara) {
        this.idTara = idTara;
    }

    public String getNomeTara() {
        return nomeTara;
    }

    public void setNomeTara(String nomeTara) {
        this.nomeTara = nomeTara;
    }

    public Float getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(Float pesoTara) {
        this.pesoTara = pesoTara;
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

        attributeParameters[0][0] = "idTara"; // Campo: txt
        attributeParameters[0][1] = "Codigo"; // Caption: txt (grid/edit layout)
        attributeParameters[0][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[0][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[0][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[0][4] = "3000"; // Grid.largura: int (grid layout)
        attributeParameters[0][5] = "2"; // Grid.alinhamento: int (grid layout)
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

        attributeParameters[1][0] = "nomeTara"; // Campo: txt
        attributeParameters[1][1] = "Designação"; // Caption: txt (grid/edit layout)
        attributeParameters[1][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[1][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[1][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[1][4] = "2500"; // Grid.largura: int (grid layout)
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

        attributeParameters[2][0] = "pesoTara"; // Campo: txt
        attributeParameters[2][1] = "Peso Tara"; // Caption: txt (grid/edit layout)
        // TODO: T_DECIMAL?
        attributeParameters[2][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[2][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[2][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[2][4] = "3450"; // Grid.largura: int (grid layout)
        attributeParameters[2][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[2][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[2][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[2][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[2][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[2][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[2][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[2][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[2][12] = "false"; // notNull: bln (validation)
        attributeParameters[2][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[2][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[2][15] = "2"; // ColIndex: int

    }

    @Override
    public String toString() {
        return "Tara{" +
                "idTara=" + idTara +
                ", nomeTara='" + nomeTara + '\'' +
                ", pesoTara=" + pesoTara +
                ", attributeParameters=" + Arrays.toString(attributeParameters) +
                '}';
    }

}
