package com.bpaulo.tipxxi.entity;

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Cartao {

    /*

       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaCartoes.bas

            |--0 ----------------|--1 ----------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
            |Campo	 			 |Caption 		|Tipo 		   |Rodape	|Grid.largura  |Grid.alinhamento |Grid.tipo_letra  |Grid.tam_letra	 |Imprimir	 |Editavel|
            |--txt---------------|--txt---------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

            array_Campos = Array(_
                Array("CartaoID",			"Cartão",			T_ALFANUM,		False, 	3000,	4,          "",				16,				    True,		True), _
                Array("CartaoIDVeiculo",	"Veículo",			T_ALFANUM,		False, 	1850,	4,          "",				0,					True,		True), _
                Array("CartaoIDArtigo",		"Artigo",			T_ALFANUM,		False, 	1800,	4, 		    "",				0, 					False,		True), _
                Array("CartaoIDEmpresa",	"Empresa",			T_ALFANUM,		False,	1800,	4,			"",				0,					False,	 	True), _
                Array("CartaoActivado",		"A;Activado",		T_BOOL & ";3",	True,	500,	4, 			T_LETRA_SYMBOL,	20,					True,		True), _
                Array("CartaoDescricao",	"Descrição",		T_ALFANUM, 		True,	0,		1,			"",				10,					False,	 	True), _
                Array("CartaoTipoOperacao",	"Tipo Operação",	T_LISTA_C06,	True,	0,		1, 			"",				0,					True,		True), _
                Array("CartaoObs",			"Observações",		T_ALFANUM, 		True,	0,		1, 			"",				0,					False,	 	True), _
                Array("ModoEmMoedeiro",		"Modo Moedeiro",	T_LISTA_C08, 	True,	0,		1, 			"",				0,					True,		True), _
                Array("SaldoCartao",		"Saldo",			T_NumDecimal, 	True,	0,		1, 			"",				0,					True, 		True), _
                Array("PlafondCartao",		"Plafond",			T_NumDecimal,	True,	0,		1,          "",				0,					True,		True), _
                Array(xDesigCampo11, 		"Desig. Artigo",	T_ALFANUM,	 	True,	0,		1,          "",				0,					True, 		False),_
                Array(xDesigCampo12,		"Nome Empresa",	    T_ALFANUM, 		True,	0,		1,          "",				0,					True,		False))

    */

    // TODO: Must be defined to create a new entity.
    private static final int NUMBER_OF_ATTRIBUTES = 11;

    // TODO: Must be defined to create a new entity.
    private static final boolean UNIQUE_REGISTER = false;

    // Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
    // Debugging: Adding "CAPTION" class variable.
    private static final String ENTITY_CAPTION = "Cartão";

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

    private static final boolean CREATE = true;
    private static final boolean READ = true;
    private static final boolean UPDATE = true;
    private static final boolean DELETE = true;

    // Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

    private String idCartao; // TODO: (Mandatory to create a new entity) Name pattern: 'id' + entity name.
    private String idVeiculo;
    private String idArtigo;
    private String idEmpresa;
    // private Integer idEmpresa;
    private Integer cartaoActivado;
    private String cartaoDescricao;
    private String cartaoTipoOperacao;
    private String cartaoObs;
    private String modoEmMoedeiro;
    // TODO: How to mapping T_NumDecimal (in comunicaxxilight as Float)?
    private Float saldoCartao;
    private Float plafondCartao;
    // TODO: How to mapping xDesigCampo11 / xDesigCampo12.

    private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

    public Cartao() {

        super();
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public Cartao(String idCartao, String idVeiculo, String idArtigo, String idEmpresa, Integer cartaoActivado, String cartaoDescricao, String cartaoTipoOperacao, String cartaoObs, String modoEmMoedeiro, Float saldoCartao, Float plafondCartao) {

        super();
        this.idCartao = idCartao;
        this.idVeiculo = idVeiculo;
        this.idArtigo = idArtigo;
        this.idEmpresa = idEmpresa;
        this.cartaoActivado = cartaoActivado;
        this.cartaoDescricao = cartaoDescricao;
        this.cartaoTipoOperacao = cartaoTipoOperacao;
        this.cartaoObs = cartaoObs;
        this.modoEmMoedeiro = modoEmMoedeiro;
        this.saldoCartao = saldoCartao;
        this.plafondCartao = plafondCartao;
        setAttributeParameters(); // TODO: Is the best place to be called?

    }

    public String getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(String idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getIdArtigo() {
        return idArtigo;
    }

    public void setIdArtigo(String idArtigo) {
        this.idArtigo = idArtigo;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getCartaoActivado() {
        return cartaoActivado;
    }

    public void setCartaoActivado(Integer cartaoActivado) {
        this.cartaoActivado = cartaoActivado;
    }

    public String getCartaoDescricao() {
        return cartaoDescricao;
    }

    public void setCartaoDescricao(String cartaoDescricao) {
        this.cartaoDescricao = cartaoDescricao;
    }

    public String getCartaoTipoOperacao() {
        return cartaoTipoOperacao;
    }

    public void setCartaoTipoOperacao(String cartaoTipoOperacao) {
        this.cartaoTipoOperacao = cartaoTipoOperacao;
    }

    public String getCartaoObs() {
        return cartaoObs;
    }

    public void setCartaoObs(String cartaoObs) {
        this.cartaoObs = cartaoObs;
    }

    public String getModoEmMoedeiro() {
        return modoEmMoedeiro;
    }

    public void setModoEmMoedeiro(String modoEmMoedeiro) {
        this.modoEmMoedeiro = modoEmMoedeiro;
    }

    public Float getSaldoCartao() {
        return saldoCartao;
    }

    public void setSaldoCartao(Float saldoCartao) {
        this.saldoCartao = saldoCartao;
    }

    public Float getPlafondCartao() {
        return plafondCartao;
    }

    public void setPlafondCartao(Float plafondCartao) {
        this.plafondCartao = plafondCartao;
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

        attributeParameters[0][0] = "idCartao"; // Campo: txt
        attributeParameters[0][1] = "Cartão"; // Caption: txt (grid/edit layout)
        attributeParameters[0][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[0][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[0][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[0][4] = "3000"; // Grid.largura: int (grid layout)
        attributeParameters[0][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[0][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[0][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[0][7] = "18"; // Grid.tam_letra: int (grid layout)
        attributeParameters[0][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[0][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[0][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[0][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[0][12] = "true"; // notNull: bln (validation)
        // attributeParameters[0][12] = "false"; // notNull: bln (validation)
        attributeParameters[0][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[0][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[0][15] = "0"; // ColIndex: int

        attributeParameters[1][0] = "idVeiculo"; // Campo: txt
        attributeParameters[1][1] = "Veículo"; // Caption: txt (grid/edit layout)
        attributeParameters[1][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[1][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[1][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[1][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[1][4] = "1850"; // Grid.largura: int (grid layout)
        attributeParameters[1][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[1][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[1][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[1][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[1][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[1][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[1][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[1][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[1][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[1][12] = "false"; // notNull: bln (validation)
        attributeParameters[1][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[1][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[1][15] = "1"; // ColIndex: int

        attributeParameters[2][0] = "idArtigo"; // Campo: txt
        attributeParameters[2][1] = "Artigo"; // Caption: txt (grid/edit layout)
        attributeParameters[2][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[2][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[2][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[2][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[2][4] = "1800"; // Grid.largura: int (grid layout)
        attributeParameters[2][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[2][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[2][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[2][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[2][7] = "0"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[2][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[2][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[2][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[2][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[2][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[2][12] = "false"; // notNull: bln (validation)
        attributeParameters[2][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[2][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[2][15] = "2"; // ColIndex: int

        attributeParameters[3][0] = "idEmpresa"; // Campo: txt
        attributeParameters[3][1] = "Empresa"; // Caption: txt (grid/edit layout)
        attributeParameters[3][2] = "T_TABELA"; // Tipo: txt (edit layout)
        // attributeParameters[3][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[3][3] = "false"; // Rodapé: bln (grid layout)
        attributeParameters[3][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[3][4] = "1800"; // Grid.largura: int (grid layout)
        attributeParameters[3][5] = "0"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[3][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[3][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[3][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[3][7] = "0"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[3][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[3][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[3][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[3][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[3][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[3][12] = "false"; // notNull: bln (validation)
        attributeParameters[3][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[3][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[3][15] = "3"; // ColIndex: int

        attributeParameters[4][0] = "cartaoActivado"; // Campo: txt
        attributeParameters[4][1] = "Cartão Activado"; // Caption: txt (grid/edit layout)
        attributeParameters[4][2] = "T_BOOL"; // Tipo: txt (edit layout)
        attributeParameters[4][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[4][4] = "150"; // Grid.largura: int (grid layout)
        // attributeParameters[4][4] = "500"; // Grid.largura: int (grid layout)
        attributeParameters[4][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[4][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[4][6] = ""; // Grid.tipo_letra: txt (grid layout)
        // TODO: T_LETRA_SYMBOL?
        // attributeParameters[4][6] = "T_LETRA_SYMBOL"; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[4][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[4][7] = "20"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[4][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[4][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[4][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[4][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[4][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[4][12] = "false"; // notNull: bln (validation)
        // attributeParameters[4][12] = "true"; // notNull: bln (validation)
        attributeParameters[4][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[4][14] = "3"; // MaxLength: int (edit layout)
        attributeParameters[4][15] = "4"; // ColIndex: int

        attributeParameters[5][0] = "cartaoDescricao"; // Campo: txt
        attributeParameters[5][1] = "Descrição"; // Caption: txt (grid/edit layout)
        attributeParameters[5][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[5][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[5][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[5][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[5][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[5][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[5][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[5][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[5][7] = "10"; // Grid.tam_letra: int (grid layout)
        attributeParameters[5][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[5][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[5][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[5][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[5][12] = "false"; // notNull: bln (validation)
        attributeParameters[5][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[5][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[5][15] = "5"; // ColIndex: int

        attributeParameters[6][0] = "cartaoTipoOperacao"; // Campo: txt
        attributeParameters[6][1] = "Tipo Operação"; // Caption: txt (grid/edit layout)
        attributeParameters[6][2] = "T_LISTA"; // Tipo: txt (edit layout)
        attributeParameters[6][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[6][4] = "150"; // Grid.largura: int (grid layout)
        // attributeParameters[6][4] = ""; // Grid.largura: int (grid layout)
        attributeParameters[6][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[6][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[6][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[6][7] = "0"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[6][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[6][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[6][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[6][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[6][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[6][12] = "false"; // notNull: bln (validation)
        attributeParameters[6][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[6][14] = "20"; // MaxLength: int (edit layout)
        attributeParameters[6][15] = "6"; // ColIndex: int

        attributeParameters[7][0] = "cartaoObs"; // Campo: txt
        attributeParameters[7][1] = "Observações"; // Caption: txt (grid/edit layout)
        attributeParameters[7][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
        attributeParameters[7][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[7][4] = "100"; // Grid.largura: int (grid layout)
        // attributeParameters[7][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[7][5] = "1"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[7][5] = "4"; // Grid.alinhamento: int (grid layout)
        attributeParameters[7][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[7][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[7][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[7][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[7][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[7][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[7][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[7][12] = "false"; // notNull: bln (validation)
        attributeParameters[7][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[7][14] = "2"; // MaxLength: int (edit layout)
        attributeParameters[7][15] = "7"; // ColIndex: int

        attributeParameters[8][0] = "modoEmMoedeiro"; // Campo: txt
        attributeParameters[8][1] = "Modo Moedeiro"; // Caption: txt (grid/edit layout)
        attributeParameters[8][2] = "T_LISTA"; // Tipo: txt (edit layout)
        attributeParameters[8][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[8][4] = "150"; // Grid.largura: int (grid layout)
        // attributeParameters[8][4] = ""; // Grid.largura: int (grid layout)
        attributeParameters[8][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[8][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[8][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[8][7] = "0"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[8][8] = "false"; // Imprimir: bln (grid layout)
        attributeParameters[8][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[8][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[8][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[8][11] = ""; // CSValoresAutorizados: txt (validation)
        attributeParameters[8][12] = "false"; // notNull: bln (validation)
        attributeParameters[8][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[8][14] = "20"; // MaxLength: int (edit layout)
        attributeParameters[8][15] = "8"; // ColIndex: int

        attributeParameters[9][0] = "saldoCartao"; // Campo: txt
        attributeParameters[9][1] = "Saldo"; // Caption: txt (grid/edit layout)
        attributeParameters[9][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[9][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[9][4] = "200"; // Grid.largura: int (grid layout)
        // attributeParameters[9][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[9][5] = "2"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[9][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[9][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[9][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[9][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[9][8] = "false"; // Imprimir: bln (grid layout)
        // attributeParameters[9][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[9][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[9][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[9][11] = ""; // CSValoresAutorizados: txt (validation)
        // attributeParameters[9][12] = "true"; // notNull: bln (validation)
        attributeParameters[9][12] = "false"; // notNull: bln (validation)
        attributeParameters[9][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[9][14] = "10"; // MaxLength: int (edit layout)
        attributeParameters[9][15] = "9"; // ColIndex: int

        attributeParameters[10][0] = "plafondCartao"; // Campo: txt
        attributeParameters[10][1] = "Plafond"; // Caption: txt (grid/edit layout)
        attributeParameters[10][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
        attributeParameters[10][3] = "true"; // Rodapé: bln (grid layout)
        attributeParameters[10][4] = "200"; // Grid.largura: int (grid layout)
        // attributeParameters[10][4] = "0"; // Grid.largura: int (grid layout)
        attributeParameters[10][5] = "2"; // Grid.alinhamento: int (grid layout)
        // attributeParameters[10][5] = "1"; // Grid.alinhamento: int (grid layout)
        attributeParameters[10][6] = ""; // Grid.tipo_letra: txt (grid layout)
        attributeParameters[10][7] = "18"; // Grid.tam_letra: int (grid layout)
        // attributeParameters[10][7] = "0"; // Grid.tam_letra: int (grid layout)
        attributeParameters[10][8] = "false"; // Imprimir: bln (grid layout)
        // attributeParameters[10][8] = "true"; // Imprimir: bln (grid layout)
        attributeParameters[10][9] = "true"; // Editavel: bln (edit layout)
        attributeParameters[10][10] = "false"; // isPassWd: bln (edit layout)
        attributeParameters[10][11] = ""; // CSValoresAutorizados: txt (validation)
        // attributeParameters[10][12] = "true"; // notNull: bln (validation)
        attributeParameters[10][12] = "false"; // notNull: bln (validation)
        attributeParameters[10][13] = ""; // CSValoresProibidos: txt (validation)
        attributeParameters[10][14] = "10"; // MaxLength: int (edit layout)
        attributeParameters[10][15] = "10"; // ColIndex: int

    }

    @Override
    public String toString() {
        return "Cartao{" +
                "idCartao='" + idCartao + '\'' +
                ", idVeiculo='" + idVeiculo + '\'' +
                ", idArtigo='" + idArtigo + '\'' +
                ", idEmpresa='" + idEmpresa + '\'' +
                ", cartaoActivado=" + cartaoActivado +
                ", cartaoDescricao='" + cartaoDescricao + '\'' +
                ", cartaoTipoOperacao='" + cartaoTipoOperacao + '\'' +
                ", cartaoObs='" + cartaoObs + '\'' +
                ", modoEmMoedeiro='" + modoEmMoedeiro + '\'' +
                ", saldoCartao=" + saldoCartao +
                ", plafondCartao=" + plafondCartao +
                ", attributeParameters=" + Arrays.toString(attributeParameters) +
                '}';
    }

}
