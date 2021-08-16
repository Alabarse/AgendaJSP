/**
 * 
 * Confirmação de exclusão de contato
 * 
 * @author Mateus Alabarse
 * @param idcon
 */

function confirmarExclusao(idcon) {
	let resposta = confirm("Confirma a exclusão desse contato ?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
}