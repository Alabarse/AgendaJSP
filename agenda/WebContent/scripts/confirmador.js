/**
 * href="delete?idcon=<%=lista.get(i).getIdCon()%>"
 * Confirmação de exclusão de contato
 * @author Mateus Alabarse
 */

function confirmarExclusao(idcon) {
	let resposta = confirm("Confirma a exclusão desse contato ?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
}