function updateOut(outputFormat) {
	var inFileName = document.getElementById('templateDocument').value;
	var index = inFileName.lastIndexOf('/');
	if (index == -1) {
		index = inFileName.lastIndexOf('\\');
	}
	var outFileName = inFileName;
	if (index != -1) {
		outFileName = outFileName.substring(index + 1, outFileName.length);
	}
	outFileName = 'Out_' + outFileName;
	if (outputFormat && outputFormat != '') {
		index = outFileName.lastIndexOf('.');
		if (index != -1) {
			outFileName = outFileName.substring(0, index);
		}
		if (outputFormat == 'PDF') {
			outputFormat = 'pdf';
		} else if (outputFormat == 'XHTML') {
			outputFormat = 'htm';
		}
		outFileName = outFileName + '.' + outputFormat;
	}
	document.getElementById('outFileName').value = outFileName;
}

function bindWithCodeMirror() {
	// wrap metadata textarea with CodeMirror by using xml mode.
	function autocompleteXML() {

	}
	var xmlTextarea = document.getElementById('metadata');
	var xmlEditor = CodeMirror.fromTextArea(xmlTextarea, {
		mode : "application/xml",
		autoCloseTags : true,
		styleActiveLine : true,
		lineNumbers : true,
		lineWrapping : true,
		extraKeys : {
			"Ctrl-Space" : "autocompleteXML"
		}
	});
	xmlEditor.on('change', function(cm) {
		xmlTextarea.value = cm.getValue();
	});

	// wrap data textarea with CodeMirror by using json mode.
	var jsonTextarea = document.getElementById('data');
	var jsonEditor = CodeMirror.fromTextArea(jsonTextarea, {
		mode : "application/json",
		autoCloseTags : true,
		styleActiveLine : true,
		lineNumbers : true,
		lineWrapping : true,
		gutters : [ "CodeMirror-lint-markers" ],
		lintWith : CodeMirror.jsonValidator
	});
	jsonEditor.on('change', function(cm) {
		jsonTextarea.value = cm.getValue();
	});

}