/**
 * Confirmação de exclusão de um contato
 *@author tavaresProg
 *@param idcon
 */

function confirmar(idcon) {
	let resposta = confirm("Deseja realmente excluir o contato?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
}
