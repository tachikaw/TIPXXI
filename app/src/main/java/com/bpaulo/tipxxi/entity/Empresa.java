package com.bpaulo.tipxxi.entity;

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Empresa {

    /*

       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaEmpresas.bas

            |--0 -------------------|--1 -------------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
            |Campo	 				|Caption 		  |Tipo 		 |Rodape  |Grid.largura	 |Grid.alinhamento |Grid.tipo_letra	 |Grid.tam_letra   |Imprimir   |Editavel|
            |--txt------------------|--txt------------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

            array_Campos = Array(_
                Array("CodEmpresa",			"Codigo",	    T_ALFANUM,	False, 	2500,           4,              "",				    0,              True, 		True), _
                Array("DesignacaoEmpresa",	"Designação",	T_ALFANUM, 	False, 	4000, 			1,              "",					0, 				True, 		True), _
                Array("EmpresaMorada",		"Morada",   	T_ALFANUM, 	True,   0,              1,              "",					0, 				True, 		True), _
                Array("EmpresaNIF",			"NIF",			T_ALFANUM, 	False, 	2450,           4, 	            "",					0, 				True, 		True))

     */

    // TODO: Must be defined to create a new entity.
    private static final int NUMBER_OF_ATTRIBUTES = 4;

    // TODO: Must be defined to create a new entity.
    private static final boolean UNIQUE_REGISTER = false;

    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
    // Debugging: Adding "CAPTION" class variable.
    private static final String ENTITY_CAPTION = "Empresa";

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

    private static final boolean CREATE = true;
    private static final boolean READ = true;
    private static final boolean UPDATE = true;
    private static final boolean DELETE = true;

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

    private String idEmpresa; // TODO: (Mandatory to create a new entity) Name pattern: 'id' + entity name.
    // private Integer idEmpresa;
    private String nomeEmpresa; // TODO: Name pattern: 'nome' + entity name.
    // private String designacaoEmpresa;
    private String empresaMorada;
    private String empresaNIF;

    private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

    public Empresa() {

        super();
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public Empresa(String idEmpresa, String nomeEmpresa, String empresaMorada, String empresaNIF) {

        super();
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.empresaMorada = empresaMorada;
        this.empresaNIF = empresaNIF;
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmpresaMorada() {
        return empresaMorada;
    }

    public void setEmpresaMorada(String empresaMorada) {
        this.empresaMorada = empresaMorada;
    }

    public String getEmpresaNIF() {
        return empresaNIF;
    }

    public void setEmpresaNIF(String empresaNIF) {
        this.empresaNIF = empresaNIF;
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

        attributeParameters[0][0] = "idEmpresa"; // Campo: txt
        attributeParameters[0][1] = "Codigo"; // Caption: txt (grid/edit layout)
        attributeParameters[0][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        // attributeParameters[0][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[0][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[0][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[0][4] = "2500"; // Grid.largura: int (grid layout)
        attributeParameters[0][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[0][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[0][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[0][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[0][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[0][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[0][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[0][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[0][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[0][12] = "true"; // notNull: bln (validation)
        // attributeParameters[0][12] = "false"; // notNull: bln (validation)
        attributeParameters[0][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[0][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[0][15] = "0"; // ColIndex: int

        attributeParameters[1][0] = "nomeEmpresa"; // Campo: txt
        attributeParameters[1][1] = "Designação"; // Caption: txt (grid/edit layout)
        attributeParameters[1][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[1][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[1][4] = "200"; // Grid.largura: int (grid layout)
        // attributeParameters[1][4] = "4000"; // Grid.largura: int (grid layout)
        attributeParameters[1][5] = "0"; // Grid.alinhamento: int (grid layout)
        attributeParameters[1][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[1][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[1][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[1][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[1][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[1][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[1][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[1][12] = "true"; // notNull: bln (validation)
        // attributeParameters[1][12] = "false"; // notNull: bln (validation)
        attributeParameters[1][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[1][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[1][15] = "1"; // ColIndex: int

        attributeParameters[2][0] = "empresaMorada"; // Campo: txt
        attributeParameters[2][1] = "Morada"; // Caption: txt (grid/edit layout)
        attributeParameters[2][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[2][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[2][4] = "200"; // Grid.largura: int (grid layout)
        // attributeParameters[2][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[2][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[2][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[2][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[2][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[2][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[2][8] = "false"; // Imprimir: bln (grid layout)
        // attributeParameters[2][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[2][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[2][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[2][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[2][12] = "false"; // notNull: bln (validation)
        attributeParameters[2][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[2][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[2][15] = "2"; // ColIndex: int

        attributeParameters[3][0] = "empresaNIF"; // Campo: txt
        attributeParameters[3][1] = "NIF"; // Caption: txt (grid/edit layout)
        attributeParameters[3][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[3][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[3][4] = "200"; // Grid.largura: int (grid layout)
        // attributeParameters[3][4] = "2450"; // Grid.largura: int (grid layout)
        attributeParameters[3][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[3][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[3][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[3][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[3][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[3][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[3][9] = "true"; // Editavel: bln (edit layout)
        // attributeParameters[3][10] = "true"; // isPassWd: bln (edit layout)
        attributeParameters[3][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[3][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[3][12] = "false"; // notNull: bln (validation)
        attributeParameters[3][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[3][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[3][15] = "3"; // ColIndex: int

    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", empresaMorada='" + empresaMorada + '\'' +
                ", empresaNIF='" + empresaNIF + '\'' +
                ", attributeParameters=" + Arrays.toString(attributeParameters) +
                '}';
    }

}
