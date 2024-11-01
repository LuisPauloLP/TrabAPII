package Graphic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DATABASE.ConnectionFactory;

public class teste {

	public static void main(String[] args) {
	    Connection conexao = null;
	    PreparedStatement selectStmt = null;
	    PreparedStatement insertEletricoStmt = null;
	    PreparedStatement insertFogoStmt = null;
	    PreparedStatement insertVoadorStmt = null;
	    PreparedStatement insertDeletadosStmt = null;
	    PreparedStatement checkDuplicateEletricoStmt = null;
	    PreparedStatement checkDuplicateFogoStmt = null;
	    PreparedStatement checkDuplicateVoadorStmt = null;
	    ResultSet rs = null;

	    try {
	        // Estabelecer conexão
	        conexao = ConnectionFactory.getConnection("localhost", "3306", "trabalho", "root", "1227");

	        // Query para selecionar pokemons
	        String selectQuery = "SELECT * FROM tb_pokemon";
	        selectStmt = conexao.prepareStatement(selectQuery);
	        rs = selectStmt.executeQuery();

	        // Prepared Statements para inserção
	        String insertEletrico = "INSERT INTO tb_pokemon_eletrico (id, pokemon, tipo) VALUES (?, ?, ?)";
	        insertEletricoStmt = conexao.prepareStatement(insertEletrico);
	        String insertFogo = "INSERT INTO tb_pokemon_fogo (id, pokemon, tipo) VALUES (?, ?, ?)";
	        insertFogoStmt = conexao.prepareStatement(insertFogo);
	        String insertVoador = "INSERT INTO tb_pokemon_voador (id, pokemon, tipo) VALUES (?, ?, ?)";
	        insertVoadorStmt = conexao.prepareStatement(insertVoador);
	        String insertDeletados = "INSERT INTO tb_pokemon_deletados (id, pokemon, tipo) VALUES (?, ?, ?)";
	        insertDeletadosStmt = conexao.prepareStatement(insertDeletados);

	        // Prepared Statements para checar duplicatas
	        String checkDuplicateEletrico = "SELECT COUNT(*) FROM tb_pokemon_eletrico WHERE pokemon = ?";
	        checkDuplicateEletricoStmt = conexao.prepareStatement(checkDuplicateEletrico);
	        String checkDuplicateFogo = "SELECT COUNT(*) FROM tb_pokemon_fogo WHERE pokemon = ?";
	        checkDuplicateFogoStmt = conexao.prepareStatement(checkDuplicateFogo);
	        String checkDuplicateVoador = "SELECT COUNT(*) FROM tb_pokemon_voador WHERE pokemon = ?";
	        checkDuplicateVoadorStmt = conexao.prepareStatement(checkDuplicateVoador);

	        // ArrayList para armazenar Pokémons processados
	        ArrayList<Pokemon> dataToMove = new ArrayList<>();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String pokemon = rs.getString("pokemon");
	            String tipo = rs.getString("tipo");

	            // Verificando duplicatas
	            int count = 0;
	            if (tipo.equalsIgnoreCase("Elétrico")) {
	                checkDuplicateEletricoStmt.setString(1, pokemon);
	                ResultSet duplicateRs = checkDuplicateEletricoStmt.executeQuery();
	                duplicateRs.next();
	                count = duplicateRs.getInt(1);

	                if (count > 0) {
	                    insertDeletadosStmt.setInt(1, id);
	                    insertDeletadosStmt.setString(2, pokemon);
	                    insertDeletadosStmt.setString(3, tipo);
	                    insertDeletadosStmt.executeUpdate();
	                } else {
	                    insertEletricoStmt.setInt(1, id);
	                    insertEletricoStmt.setString(2, pokemon);
	                    insertEletricoStmt.setString(3, tipo);
	                    insertEletricoStmt.executeUpdate();
	                }
	            } else if (tipo.equalsIgnoreCase("Fogo")) {
	                checkDuplicateFogoStmt.setString(1, pokemon);
	                ResultSet duplicateRs = checkDuplicateFogoStmt.executeQuery();
	                duplicateRs.next();
	                count = duplicateRs.getInt(1);

	                if (count > 0) {
	                    insertDeletadosStmt.setInt(1, id);
	                    insertDeletadosStmt.setString(2, pokemon);
	                    insertDeletadosStmt.setString(3, tipo);
	                    insertDeletadosStmt.executeUpdate();
	                } else {
	                    insertFogoStmt.setInt(1, id);
	                    insertFogoStmt.setString(2, pokemon);
	                    insertFogoStmt.setString(3, tipo);
	                    insertFogoStmt.executeUpdate();
	                }
	            } else if (tipo.equalsIgnoreCase("Voador")) {
	                checkDuplicateVoadorStmt.setString(1, pokemon);
	                ResultSet duplicateRs = checkDuplicateVoadorStmt.executeQuery();
	                duplicateRs.next();
	                count = duplicateRs.getInt(1);

	                if (count > 0) {
	                    insertDeletadosStmt.setInt(1, id);
	                    insertDeletadosStmt.setString(2, pokemon);
	                    insertDeletadosStmt.setString(3, tipo);
	                    insertDeletadosStmt.executeUpdate();
	                } else {
	                    insertVoadorStmt.setInt(1, id);
	                    insertVoadorStmt.setString(2, pokemon);
	                    insertVoadorStmt.setString(3, tipo);
	                    insertVoadorStmt.executeUpdate();
	                }
	            }

	            Pokemon p = new Pokemon(id, pokemon, tipo, count > 0);
	            dataToMove.add(p);
	        }

	        // Atualizando a tabela totalizadora
	        updateTotalizador(conexao, dataToMove);

	        System.out.println("Processamento concluído!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Fechando conexões e statements
	        try {
	            if (rs != null) rs.close();
	            if (selectStmt != null) selectStmt.close();
	            if (insertEletricoStmt != null) insertEletricoStmt.close();
	            if (insertFogoStmt != null) insertFogoStmt.close();
	            if (insertVoadorStmt != null) insertVoadorStmt.close();
	            if (insertDeletadosStmt != null) insertDeletadosStmt.close();
	            if (checkDuplicateEletricoStmt != null) checkDuplicateEletricoStmt.close();
	            if (checkDuplicateFogoStmt != null) checkDuplicateFogoStmt.close();
	            if (checkDuplicateVoadorStmt != null) checkDuplicateVoadorStmt.close();
	            if (conexao != null) conexao.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


    private static void updateTotalizador(Connection conn, ArrayList<Pokemon> pokemons) throws SQLException {
        // Map para contar a quantidade de cada tipo e duplicados
        Map<String, Integer> quantidadePorTipo = new HashMap<>();
        Map<String, Integer> duplicadosPorTipo = new HashMap<>();

        for (Pokemon p : pokemons) {
            quantidadePorTipo.put(p.getTipo(), quantidadePorTipo.getOrDefault(p.getTipo(), 0) + 1);
            if (p.isDuplicado()) {
                duplicadosPorTipo.put(p.getTipo(), duplicadosPorTipo.getOrDefault(p.getTipo(), 0) + 1);
            }
        }

        // Prepared Statement para inserir/atualizar totalizador
        String insertOrUpdateTotalizador = "INSERT INTO tb_pokemon_totalizador (tipo, quantidade, quantidade_duplicados) "
                + "VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE quantidade = VALUES(quantidade), quantidade_duplicados = VALUES(quantidade_duplicados)";
        PreparedStatement totalizadorStmt = conn.prepareStatement(insertOrUpdateTotalizador);

        for (Map.Entry<String, Integer> entry : quantidadePorTipo.entrySet()) {
            String tipo = entry.getKey();
            int quantidade = entry.getValue();
            int quantidadeDuplicados = duplicadosPorTipo.getOrDefault(tipo, 0);

            totalizadorStmt.setString(1, tipo);
            totalizadorStmt.setInt(2, quantidade);
            totalizadorStmt.setInt(3, quantidadeDuplicados);
            totalizadorStmt.executeUpdate();
        }

        totalizadorStmt.close();
    }
}

// Classe Pokemon para armazenar informações
class Pokemon {
    private int id;
    private String pokemon;
    private String tipo;
    private boolean duplicado;

    public Pokemon(int id, String pokemon, String tipo, boolean duplicado) {
        this.id = id;
        this.pokemon = pokemon;
        this.tipo = tipo;
        this.duplicado = duplicado;
    }

    public int getId() {
        return id;
    }

    public String getPokemon() {
        return pokemon;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDuplicado() {
        return duplicado;
    }
}