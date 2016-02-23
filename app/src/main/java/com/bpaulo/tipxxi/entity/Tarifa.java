package com.bpaulo.tipxxi.entity;

import com.bpaulo.tipxxi.database.DatabaseHelper;

import java.util.Arrays;

public class Tarifa {

    /*

       C:\Users\User\Dropbox\NSBasic\_Pacote_AFY07OPIPPC-CE\TIPXXI_Menu_Principal\Modulos\mdl_Form_TabelaTarifas.bas

			|--0 -------------------|--1 -------------|--2 ----------|--3 ----|--4 ----------|--5 -------------|--6 -------------|--7 -------------|--8 -------|--9 ----|
			|Campo	 				|Caption 		  |Tipo 		 |Rodape  |Grid.largura	 |Grid.alinhamento |Grid.tipo_letra	 |Grid.tam_letra   |Imprimir   |Editavel|
			|--txt------------------|--txt------------|--txt---------|--bln---|--int---------|--int------------|--txt------------|--int------------|--bln------|--bln---|

			array_Campos = Array(_
				Array("IDTarifa",		"ID",			T_ALFANUM,		False, 	1500, 			4, 				"",					0, 				False, 		True), _
				Array("PesoMinEscalao",	"Peso Mínimo",	T_ALFANUM,		False, 	2480, 			7, 				"",					0, 				False, 		True), _
				Array("PesoMaxEscalao",	"Peso Máximo",	T_ALFANUM,		False, 	2480, 			7, 				"",					0, 				False, 		True), _
				Array("TarifaEscalao",	"Preço", 		T_NumDecimal, 	False, 	2490, 			7, 				"",					0, 				False, 		True) )

    */

	// TODO: Must be defined to create a new entity.
	private static final int NUMBER_OF_ATTRIBUTES = 4;

	// TODO: Must be defined to create a new entity.
	private static final boolean UNIQUE_REGISTER = false; // TODO // true = / false =

	// Debugging (tipxxi): Changing "CAPTION" class variable name to "ENTITY_CAPTION".
	// Debugging: Adding "CAPTION" class variable.
	private static final String ENTITY_CAPTION = "Tarifa";

	// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (begin).

	private static final boolean CREATE = true; // TODO // true = / false =
	private static final boolean READ = false; // TODO // true = / false =
	private static final boolean UPDATE = true; // TODO // true = / false =
	private static final boolean DELETE = true; // TODO // true = / false =

	// Debugging (tipxxi): Entity access feature: (C)reate / (R)ead / (U)pdate / (D)elete (end).

	private String idTarifa; // TODO: (Mandatory to create a new entity) Name pattern: 'id' + entity name.
	// private Integer idTarifa;
	private String pesoMinEscalao;
	private String pesoMaxEscalao;
	private Float tarifaEscalao;

	private String[][] attributeParameters = new String[NUMBER_OF_ATTRIBUTES][DatabaseHelper.NUMBER_OF_ATTRIBUTE_PARAMETERS];

	public Tarifa() {

		super();
		setAttributeParameters(); // TODO: Is the best place to be called?

	}

	public Tarifa(String idTarifa, String pesoMinEscalao, String pesoMaxEscalao, Float tarifaEscalao) {

		super();
		this.idTarifa = idTarifa;
		this.pesoMinEscalao = pesoMinEscalao;
		this.pesoMaxEscalao = pesoMaxEscalao;
		this.tarifaEscalao = tarifaEscalao;
		setAttributeParameters(); // TODO: Is the best place to be called?

	}

	public String getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(String idTarifa) {
		this.idTarifa = idTarifa;
	}

	public String getPesoMinEscalao() {
		return pesoMinEscalao;
	}

	public void setPesoMinEscalao(String pesoMinEscalao) {
		this.pesoMinEscalao = pesoMinEscalao;
	}

	public String getPesoMaxEscalao() {
		return pesoMaxEscalao;
	}

	public void setPesoMaxEscalao(String pesoMaxEscalao) {
		this.pesoMaxEscalao = pesoMaxEscalao;
	}

	public Float getTarifaEscalao() {
		return tarifaEscalao;
	}

	public void setTarifaEscalao(Float tarifaEscalao) {
		this.tarifaEscalao = tarifaEscalao;
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

		attributeParameters[0][0] = "idTarifa"; // Campo: txt
		attributeParameters[0][1] = "ID"; // Caption: txt (grid/edit layout)
		attributeParameters[0][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
		attributeParameters[0][3] = "false"; // Rodapé: bln (grid layout)
		attributeParameters[0][4] = "100"; // Grid.largura: int (grid layout)
		// attributeParameters[0][4] = "1500"; // Grid.largura: int (grid layout)
		attributeParameters[0][5] = "0"; // Grid.alinhamento: int (grid layout)
		// attributeParameters[0][5] = "4"; // Grid.alinhamento: int (grid layout)
		attributeParameters[0][6] = ""; // Grid.tipo_letra: txt (grid layout)
		attributeParameters[0][7] = "18"; // Grid.tam_letra: int (grid layout)
		// attributeParameters[0][7] = "0"; // Grid.tam_letra: int (grid layout)
		attributeParameters[0][8] = "true"; // Imprimir: bln (grid layout)
		// attributeParameters[0][8] = "false"; // Imprimir: bln (grid layout)
		attributeParameters[0][9] = "true"; // Editavel: bln (edit layout)
		attributeParameters[0][10] = "false"; // isPassWd: bln (edit layout)
		attributeParameters[0][11] = ""; // CSValoresAutorizados: txt (validation)
		attributeParameters[0][12] = "true"; // notNull: bln (validation)
		// attributeParameters[0][12] = "false"; // notNull: bln (validation)
		attributeParameters[0][13] = ""; // CSValoresProibidos: txt (validation)
		attributeParameters[0][14] = "2"; // MaxLength: int (edit layout)
		attributeParameters[0][15] = "0"; // ColIndex: int

		attributeParameters[1][0] = "pesoMinEscalao"; // Campo: txt
		attributeParameters[1][1] = "Peso Mínimo"; // Caption: txt (grid/edit layout)
		attributeParameters[1][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
		attributeParameters[1][3] = "false"; // Rodapé: bln (grid layout)
		attributeParameters[1][4] = "200"; // Grid.largura: int (grid layout)
		// attributeParameters[1][4] = "2480"; // Grid.largura: int (grid layout)
		attributeParameters[1][5] = "0"; // Grid.alinhamento: int (grid layout)
		// attributeParameters[1][5] = "7"; // Grid.alinhamento: int (grid layout)
		attributeParameters[1][6] = ""; // Grid.tipo_letra: txt (grid layout)
		attributeParameters[1][7] = "18"; // Grid.tam_letra: int (grid layout)
		// attributeParameters[1][7] = "0"; // Grid.tam_letra: int (grid layout)
		attributeParameters[1][8] = "true"; // Imprimir: bln (grid layout)
		// attributeParameters[1][8] = "false"; // Imprimir: bln (grid layout)
		attributeParameters[1][9] = "true"; // Editavel: bln (edit layout)
		attributeParameters[1][10] = "false"; // isPassWd: bln (edit layout)
		attributeParameters[1][11] = ""; // CSValoresAutorizados: txt (validation)
		attributeParameters[1][12] = "false"; // notNull: bln (validation)
		attributeParameters[1][13] = ""; // CSValoresProibidos: txt (validation)
		attributeParameters[1][14] = "2"; // MaxLength: int (edit layout)
		attributeParameters[1][15] = "1"; // ColIndex: int

		attributeParameters[2][0] = "pesoMaxEscalao"; // Campo: txt
		attributeParameters[2][1] = "Peso Máximo"; // Caption: txt (grid/edit layout)
		attributeParameters[2][2] = "T_ALFANUM"; // Tipo: txt (edit layout)
		attributeParameters[2][3] = "false"; // Rodapé: bln (grid layout)
		attributeParameters[2][4] = "200"; // Grid.largura: int (grid layout)
		// attributeParameters[2][4] = "2480"; // Grid.largura: int (grid layout)
		attributeParameters[2][5] = "0"; // Grid.alinhamento: int (grid layout)
		// attributeParameters[2][5] = "7"; // Grid.alinhamento: int (grid layout)
		attributeParameters[2][6] = ""; // Grid.tipo_letra: txt (grid layout)
		attributeParameters[2][7] = "18"; // Grid.tam_letra: int (grid layout)
		// attributeParameters[2][7] = "0"; // Grid.tam_letra: int (grid layout)
		attributeParameters[2][8] = "true"; // Imprimir: bln (grid layout)
		// attributeParameters[2][8] = "false"; // Imprimir: bln (grid layout)
		attributeParameters[2][9] = "true"; // Editavel: bln (edit layout)
		attributeParameters[2][10] = "false"; // isPassWd: bln (edit layout)
		attributeParameters[2][11] = ""; // CSValoresAutorizados: txt (validation)
		attributeParameters[2][12] = "false"; // notNull: bln (validation)
		attributeParameters[2][13] = ""; // CSValoresProibidos: txt (validation)
		attributeParameters[2][14] = "2"; // MaxLength: int (edit layout)
		attributeParameters[2][15] = "2"; // ColIndex: int

		attributeParameters[3][0] = "tarifaEscalao"; // Campo: txt
		attributeParameters[3][1] = "Preço"; // Caption: txt (grid/edit layout)
		// TODO: T_DECIMAL?
		attributeParameters[3][2] = "T_NUMERICO"; // Tipo: txt (edit layout)
		attributeParameters[3][3] = "false"; // Rodapé: bln (grid layout)
		attributeParameters[3][4] = "100"; // Grid.largura: int (grid layout)
		// attributeParameters[3][4] = "2490"; // Grid.largura: int (grid layout)
		attributeParameters[3][5] = "1"; // Grid.alinhamento: int (grid layout)
		// attributeParameters[3][5] = "7"; // Grid.alinhamento: int (grid layout)
		attributeParameters[3][6] = ""; // Grid.tipo_letra: txt (grid layout)
		attributeParameters[3][7] = "18"; // Grid.tam_letra: int (grid layout)
		// attributeParameters[3][7] = "0"; // Grid.tam_letra: int (grid layout)
		attributeParameters[3][8] = "true"; // Imprimir: bln (grid layout)
		// attributeParameters[3][8] = "false"; // Imprimir: bln (grid layout)
		attributeParameters[3][9] = "true"; // Editavel: bln (edit layout)
		attributeParameters[3][10] = "false"; // isPassWd: bln (edit layout)
		attributeParameters[3][11] = ""; // CSValoresAutorizados: txt (validation)
		attributeParameters[3][12] = "false"; // notNull: bln (validation)
		attributeParameters[3][13] = ""; // CSValoresProibidos: txt (validation)
		attributeParameters[3][14] = "2"; // MaxLength: int (edit layout)
		attributeParameters[3][15] = "3"; // ColIndex: int

	}

	@Override
	public String toString() {
		return "Tarifa{" +
				"idTarifa=" + idTarifa +
				", pesoMinEscalao='" + pesoMinEscalao + '\'' +
				", pesoMaxEscalao='" + pesoMaxEscalao + '\'' +
				", tarifaEscalao=" + tarifaEscalao +
				", attributeParameters=" + Arrays.toString(attributeParameters) +
				'}';
	}

}
