package com.bpaulo.tipxxi.entity;

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Parque {

    /*

       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaParque.bas

            |--0 ----------------|--1 ----------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
            |Campo	 			 |Caption 		|Tipo 		   |Rodape	|Grid.largura  |Grid.alinhamento |Grid.tipo_letra  |Grid.tam_letra	 |Imprimir	 |Editavel|
            |--txt---------------|--txt---------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

            array_Campos = Array(_
                Array("NumPesagem",	"Pesagem",	    T_ALFANUM,	False, 	    1500, 		4, 				    "",				0, 				    True, 	    False), _
                Array("IDVeiculo",	"Matricula",	T_ALFANUM,	False, 	    2000, 		4, 					"",				0, 					True, 		False), _
                Array("DataPesagem","Data",			T_ALFANUM,	False, 	    2000, 		4, 					"",				0, 					True, 		False), _
                Array("horaPesagem","Hora",			T_ALFANUM,	False, 	    1650, 		4, 					"",				0, 					True, 		False), _
                Array("Bruto",		"Peso (kg)",	T_ALFANUM, 	False, 	    1800,		7, 					"",				0, 					True, 		False), _
                Array("IDSerie",	"Serie",		T_ALFANUM, 	False,	    0,			4, 					"",				0, 					False, 		False))

    */

    // TODO: Must be defined to create a new entity.
    private static final int NUMBER_OF_ATTRIBUTES = 6;

    // TODO: Must be defined to create a new entity.
    private static final boolean UNIQUE_REGISTER = false;

    // Debugging: Adding "CAPTION" class variable.
    private static final String ENTITY_CAPTION = "Parque";

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

    private static final boolean CREATE = true;
    private static final boolean READ = true;
    private static final boolean UPDATE = true;
    private static final boolean DELETE = true;

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

    // TODO: idParque not defined?
    private String numPesagem;
    private String idVeiculo;
    // private Integer idVeiculo;
    private String dataPesagem;
    private String horaPesagem;
    private String bruto;
    private String idSerie; // TODO: "Serie" table?
    // private Integer idSerie;

    private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

    public Parque() {

        super();
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public Parque(String numPesagem, String idVeiculo, String dataPesagem, String horaPesagem, String bruto, String idSerie) {

        super();
        this.numPesagem = numPesagem;
        this.idVeiculo = idVeiculo;
        this.dataPesagem = dataPesagem;
        this.horaPesagem = horaPesagem;
        this.bruto = bruto;
        this.idSerie = idSerie;
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public String getNumPesagem() {
        return numPesagem;
    }

    public void setNumPesagem(String numPesagem) {
        this.numPesagem = numPesagem;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(String idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getDataPesagem() {
        return dataPesagem;
    }

    public void setDataPesagem(String dataPesagem) {
        this.dataPesagem = dataPesagem;
    }

    public String getHoraPesagem() {
        return horaPesagem;
    }

    public void setHoraPesagem(String horaPesagem) {
        this.horaPesagem = horaPesagem;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public String[] getAttributeParameters(String pAttribute) {

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

        attributeParameters[0][0] = "numPesagem"; // Campo: txt
        attributeParameters[0][1] = "Pesagem"; // Caption: txt (grid/edit layout)
        attributeParameters[0][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[0][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[0][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[0][4] = "1500"; // Grid.largura: int (grid layout)
        attributeParameters[0][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[0][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[0][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[0][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[0][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[0][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[0][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[0][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[0][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[0][12] = "false"; // notNull: bln (validation)
        attributeParameters[0][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[0][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[0][15] = "0"; // ColIndex: int

        attributeParameters[1][0] = "idVeiculo"; // Campo: txt
        attributeParameters[1][1] = "Matricula"; // Caption: txt (grid/edit layout)
        attributeParameters[1][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[1][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[1][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[1][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[1][4] = "2000"; // Grid.largura: int (grid layout)
        attributeParameters[1][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[1][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[1][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[1][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[1][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[1][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[1][9] = "true"; // Editavel: bln (edit layout)
        // attributeParameters[1][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[1][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[1][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[1][12] = "false"; // notNull: bln (validation)
        attributeParameters[1][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[1][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[1][15] = "1"; // ColIndex: int

        attributeParameters[2][0] = "dataPesagem"; // Campo: txt
        attributeParameters[2][1] = "Data"; // Caption: txt (grid/edit layout)
        attributeParameters[2][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[2][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[2][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[2][4] = "2000"; // Grid.largura: int (grid layout)
        attributeParameters[2][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[2][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[2][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[2][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[2][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[2][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[2][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[2][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[2][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[2][12] = "false"; // notNull: bln (validation)
        attributeParameters[2][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[2][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[2][15] = "2"; // ColIndex: int

        attributeParameters[3][0] = "horaPesagem"; // Campo: txt
        attributeParameters[3][1] = "Hora"; // Caption: txt (grid/edit layout)
        attributeParameters[3][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[3][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[3][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[3][4] = "1650"; // Grid.largura: int (grid layout)
        attributeParameters[3][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[3][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[3][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[3][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[3][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[3][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[3][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[3][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[3][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[3][12] = "false"; // notNull: bln (validation)
        attributeParameters[3][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[3][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[3][15] = "3"; // ColIndex: int

        attributeParameters[4][0] = "bruto"; // Campo: txt
        attributeParameters[4][1] = "Peso (kg)"; // Caption: txt (grid/edit layout)
        attributeParameters[4][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[4][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[4][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[4][4] = "1800"; // Grid.largura: int (grid layout)
        attributeParameters[4][5] = "2"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[4][5] = "7"; // Grid.alinhamento: int (grid layout)
        attributeParameters[4][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[4][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[4][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[4][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[4][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[4][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[4][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[4][12] = "false"; // notNull: bln (validation)
        attributeParameters[4][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[4][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[4][15] = "4"; // ColIndex: int

        attributeParameters[5][0] = "idSerie"; // Campo: txt
        attributeParameters[5][1] = "Serie"; // Caption: txt (grid/edit layout)
        attributeParameters[5][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[5][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[5][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[5][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[5][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[5][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[5][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[5][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[5][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[5][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[5][9] = "true"; // Editavel: bln (edit layout)
        // attributeParameters[5][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[5][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[5][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[5][12] = "false"; // notNull: bln (validation)
        attributeParameters[5][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[5][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[5][15] = "5"; // ColIndex: int

    }

    @Override
    public String toString() {
        return "Parque{" +
                "numPesagem='" + numPesagem + '\'' +
                ", idVeiculo=" + idVeiculo +
                ", dataPesagem='" + dataPesagem + '\'' +
                ", horaPesagem='" + horaPesagem + '\'' +
                ", bruto='" + bruto + '\'' +
                ", idSerie=" + idSerie +
                ", attributeParameters=" + Arrays.toString(attributeParameters) +
                '}';
    }

}