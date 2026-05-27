export const getEditorKey = (

    problemId,
    language

) => {

    return `code_${problemId}_${language}`;

};


export const saveCode = (

    problemId,
    language,
    code

) => {

    localStorage.setItem(

        getEditorKey(
            problemId,
            language
        ),

        code

    );

};


export const loadCode = (

    problemId,
    language

) => {

    return localStorage.getItem(

        getEditorKey(
            problemId,
            language
        )

    );

};