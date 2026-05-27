import Editor from "@monaco-editor/react";

function CodeEditor({

    code,
    setCode,
    language

}) {

    const getEditorLanguage = () => {

        switch (language) {

            case "JAVA":
                return "java";

            case "PYTHON":
                return "python";

            case "JAVASCRIPT":
                return "javascript";

            default:
                return "plaintext";

        }

    };

    const handleChange = (value) => {

        setCode(
            value || ""
        );

    };

    return (

        <Editor
            height="80vh"
            language={
                getEditorLanguage()
            }
            value={
                code || ""
            }
            theme="vs-dark"
            onChange={
                handleChange
            }
        />

    );

}

export default CodeEditor;