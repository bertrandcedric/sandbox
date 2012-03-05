function arriver() {
	dirBonjour(function() {
		alert('Action a executer apres avoir dit bonjour');
	});
}

function dirBonjour() {
	alert('Bonjour');

	if(arguments[0]) {
		arguments[0]();
	}
}

arriver();