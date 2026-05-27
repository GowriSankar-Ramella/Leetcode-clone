function LanguageSelector({
    language,
    setLanguage
}) {

    return (

        <select
            value={language}
            onChange={(e) =>
                setLanguage(
                    e.target.value
                )
            }
            className="
            border
            rounded
            p-2
            "
        >

            <option value="JAVA">
                Java
            </option>

            <option value="PYTHON">
                Python
            </option>

            <option value="JAVASCRIPT">
                JavaScript
            </option>

        </select>

    );

}

export default LanguageSelector;