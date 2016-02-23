package com.bpaulo.tipxxi.entity;

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Historico {

    /*

       \NSBasic\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaHistorico.bas
       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaHistorico.bas

            |--0 ----------------|--1 ----------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
            |Campo	 			 |Caption 		|Tipo 		   |Rodape	|Grid.largura  |Grid.alinhamento |Grid.tipo_letra  |Grid.tam_letra	 |Imprimir	 |Editavel|
            |--txt---------------|--txt---------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

            array_Campos = Array(_
                Array("NumPesagem",			"Pesagem",		T_ALFANUM, 		False,	1500,	4, 			    "",				    0, 			    True, 		False), _
                Array("IDVeiculo",			"Matricula",	T_ALFANUM, 		False,	2000,	4, 				"",					0, 				True, 		False), _
                Array("DataPesagem",		"Data",			T_ALFANUM, 		False,	2000,	4, 				"",					0, 				True, 		False), _
                Array("HoraPesagem",		"Hora",			T_ALFANUM, 		False,	1550,	4, 				"",					0, 				True, 		False), _
                Array("Liquido",			"LQD (kg)",		T_ALFANUM, 		False,	1900,	7, 				"",					0, 				True, 		False), _
                Array("Bruto",				"Peso 1 (kg)",	T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("Tara",				"Peso 2 (kg)",	T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("IDEmpresa",			"ID Empresa",	T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("DesignacaoEmpresa",	"Empresa",		T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("EmpresaMorada",		"Morada",		T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("EmpresaNIF",			"NIF",			T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("IDSerie",			"Serie",		T_ALFANUM, 		False,	0,		4, 				"",					0, 				False, 		False), _
                Array("IDOperador",			"Operador",		T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False), _
                Array("TarifaCobrada",		"Valor Tarifa", T_NumDecimal, 	True,	0,		4, 				"",					0, 				True, 		False), _
                Array("ValorRecebido",		"Valor Pago",	T_NumDecimal, 	True,	0,		4, 				"",					0, 				True, 		False), _
                Array("NumDocFR",			"N. Doc FR",	T_ALFANUM, 		True,	0,		4, 				"",					0, 				True, 		False))

    */

    // TODO: Must be defined to create a new entity.
    private static final int NUMBER_OF_ATTRIBUTES = 16;

    // TODO: Must be defined to create a new entity.
    private static final boolean UNIQUE_REGISTER = false;

    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
    // Debugging: Adding "CAPTION" class variable.
    private static final String ENTITY_CAPTION = "Histórico";

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

    private static final boolean CREATE = true;
    private static final boolean READ = true;
    private static final boolean UPDATE = true;
    private static final boolean DELETE = true;

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

    // TODO: idHistorico not defined?
    private String numPesagem;
    private String idVeiculo;
    // private Integer idVeiculo;
    private String dataPesagem;
    private String horaPesagem;
    private String liquido;
    private String bruto;
    private String tara;
    private Integer idEmpresa;
    // Debugging (tipxxi): Name pattern: 'nome' + entity name.
    private String nomeEmpresa;
    // private String designacaoEmpresa;
    private String empresaMorada;
    private String empresaNIF;
    private String idSerie; // TODO: "Serie" table?
    // private Integer idSerie;
    private String idOperador;
    // private Integer idOperador;
    private Float tarifaCobrada;
    private Float valorRecebido;
    private String numDocFR;

    private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

    public Historico() {

        super();
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public Historico(String numPesagem, String idVeiculo, String dataPesagem, String horaPesagem, String liquido, String bruto, String tara, Integer idEmpresa, String nomeEmpresa, String empresaMorada, String empresaNIF, String idSerie, String idOperador, Float tarifaCobrada, Float valorRecebido, String numDocFR) {

        super();
        this.numPesagem = numPesagem;
        this.idVeiculo = idVeiculo;
        this.dataPesagem = dataPesagem;
        this.horaPesagem = horaPesagem;
        this.liquido = liquido;
        this.bruto = bruto;
        this.tara = tara;
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.empresaMorada = empresaMorada;
        this.empresaNIF = empresaNIF;
        this.idSerie = idSerie;
        this.idOperador = idOperador;
        this.tarifaCobrada = tarifaCobrada;
        this.valorRecebido = valorRecebido;
        this.numDocFR = numDocFR;
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

    public String getLiquido() {
        return liquido;
    }

    public void setLiquido(String liquido) {
        this.liquido = liquido;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
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

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public String getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    public Float getTarifaCobrada() {
        return tarifaCobrada;
    }

    public void setTarifaCobrada(Float tarifaCobrada) {
        this.tarifaCobrada = tarifaCobrada;
    }

    public Float getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Float valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getNumDocFR() {
        return numDocFR;
    }

    public void setNumDocFR(String numDocFR) {
        this.numDocFR = numDocFR;
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

        attributeParameters[0][0] = "numPesagem"; // Campo: txt
        attributeParameters[0][1] = "ID"; // Caption: txt (grid/edit layout)
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
        // attributeParameters[3][4] = "1550"; // Grid.largura: int (grid layout)
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

        attributeParameters[4][0] = "liquido"; // Campo: txt
        attributeParameters[4][1] = "LQD (kg)"; // Caption: txt (grid/edit layout)
        attributeParameters[4][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[4][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[4][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[4][4] = "1900"; // Grid.largura: int (grid layout)
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

        attributeParameters[5][0] = "bruto"; // Campo: txt
        attributeParameters[5][1] = "Peso 1 (kg)"; // Caption: txt (grid/edit layout)
        attributeParameters[5][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[5][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[5][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[5][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[5][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[5][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[5][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[5][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[5][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[5][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[5][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[5][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[5][12] = "false"; // notNull: bln (validation)
        attributeParameters[5][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[5][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[5][15] = "5"; // ColIndex: int

        attributeParameters[6][0] = "tara"; // Campo: txt
        attributeParameters[6][1] = "Peso 2 (kg)"; // Caption: txt (grid/edit layout)
        attributeParameters[6][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[6][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[6][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[6][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[6][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[6][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[6][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[6][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[6][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[6][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[6][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[6][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[6][12] = "false"; // notNull: bln (validation)
        attributeParameters[6][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[6][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[6][15] = "6"; // ColIndex: int

        attributeParameters[7][0] = "idEmpresa"; // Campo: txt
        attributeParameters[7][1] = "ID Empresa"; // Caption: txt (grid/edit layout)
        attributeParameters[7][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[7][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[7][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[7][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[7][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[7][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[7][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[7][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[7][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[7][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[7][9] = "true"; // Editavel: bln (edit layout)
        // attributeParameters[7][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[7][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[7][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[7][12] = "false"; // notNull: bln (validation)
        attributeParameters[7][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[7][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[7][15] = "7"; // ColIndex: int

        attributeParameters[8][0] = "nomeEmpresa"; // Campo: txt
        attributeParameters[8][1] = "Empresa"; // Caption: txt (grid/edit layout)
        attributeParameters[8][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[8][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[8][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[8][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[8][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[8][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[8][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[8][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[8][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[8][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[8][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[8][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[8][12] = "false"; // notNull: bln (validation)
        attributeParameters[8][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[8][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[8][15] = "8"; // ColIndex: int

        attributeParameters[9][0] = "empresaMorada"; // Campo: txt
        attributeParameters[9][1] = "Morada"; // Caption: txt (grid/edit layout)
        attributeParameters[9][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[9][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[9][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[9][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[9][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[9][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[9][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[9][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[9][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[9][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[9][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[9][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[9][12] = "false"; // notNull: bln (validation)
        attributeParameters[9][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[9][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[9][15] = "9"; // ColIndex: int

        attributeParameters[10][0] = "empresaNIF"; // Campo: txt
        attributeParameters[10][1] = "NIF"; // Caption: txt (grid/edit layout)
        attributeParameters[10][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[10][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[10][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[10][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[10][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[10][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[10][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[10][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[10][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[10][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[10][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[10][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[10][12] = "false"; // notNull: bln (validation)
        attributeParameters[10][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[10][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[10][15] = "10"; // ColIndex: int

        attributeParameters[11][0] = "idSerie"; // Campo: txt
        attributeParameters[11][1] = "Serie"; // Caption: txt (grid/edit layout)
        attributeParameters[11][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[11][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[11][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[11][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[11][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[11][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[11][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[11][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[11][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[11][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[11][9] = "true"; // Editavel: bln (edit layout)
        // attributeParameters[11][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[11][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[11][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[11][12] = "false"; // notNull: bln (validation)
        attributeParameters[11][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[11][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[11][15] = "11"; // ColIndex: int

        attributeParameters[12][0] = "idOperador"; // Campo: txt
        attributeParameters[12][1] = "Operador"; // Caption: txt (grid/edit layout)
        attributeParameters[12][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[12][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[12][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[12][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[12][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[12][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[12][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[12][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[12][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[12][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[12][9] = "true"; // Editavel: bln (edit layout)
        // attributeParameters[12][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[12][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[12][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[12][12] = "false"; // notNull: bln (validation)
        attributeParameters[12][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[12][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[12][15] = "12"; // ColIndex: int

        attributeParameters[13][0] = "tarifaCobrada"; // Campo: txt
        attributeParameters[13][1] = "Valor Tarifa"; // Caption: txt (grid/edit layout)
        // TODO: T_DECIMAL?
        attributeParameters[13][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[13][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[13][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[13][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[13][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[13][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[13][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[13][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[13][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[13][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[13][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[13][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[13][12] = "false"; // notNull: bln (validation)
        attributeParameters[13][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[13][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[13][15] = "13"; // ColIndex: int

        attributeParameters[14][0] = "valorRecebido"; // Campo: txt
        attributeParameters[14][1] = "Valor Pago"; // Caption: txt (grid/edit layout)
        // TODO: T_DECIMAL?
        attributeParameters[14][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[14][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[14][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[14][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[14][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[14][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[14][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[14][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[14][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[14][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[14][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[14][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[14][12] = "false"; // notNull: bln (validation)
        attributeParameters[14][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[14][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[14][15] = "14"; // ColIndex: int

        attributeParameters[15][0] = "numDocFR"; // Campo: txt
        attributeParameters[15][1] = "N. Doc FR"; // Caption: txt (grid/edit layout)
        attributeParameters[15][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[15][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[15][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[15][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[15][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[15][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[15][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[15][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[15][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[15][9] = "false"; // Editavel: bln (edit layout)
        attributeParameters[15][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[15][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[15][12] = "false"; // notNull: bln (validation)
        attributeParameters[15][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[15][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[15][15] = "15"; // ColIndex: int

    }

    @Override
    public String toString() {
        return "Historico{" +
                "numPesagem='" + numPesagem + '\'' +
                ", idVeiculo=" + idVeiculo +
                ", dataPesagem='" + dataPesagem + '\'' +
                ", horaPesagem='" + horaPesagem + '\'' +
                ", liquido='" + liquido + '\'' +
                ", bruto='" + bruto + '\'' +
                ", tara='" + tara + '\'' +
                ", idEmpresa=" + idEmpresa +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", empresaMorada='" + empresaMorada + '\'' +
                ", empresaNIF='" + empresaNIF + '\'' +
                ", idSerie=" + idSerie +
                ", idOperador=" + idOperador +
                ", tarifaCobrada=" + tarifaCobrada +
                ", valorRecebido=" + valorRecebido +
                ", numDocFR='" + numDocFR + '\'' +
                ", attributeParameters=" + Arrays.toString(attributeParameters) +
                '}';
    }

}
