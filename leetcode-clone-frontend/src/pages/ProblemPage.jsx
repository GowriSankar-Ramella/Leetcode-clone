import {
    useEffect,
    useState
}
    from "react";

import {
    useParams
}
    from "react-router-dom";

import {
    getProblemById
}
    from "../services/problemService";

import CodeEditor
    from "../components/problem/CodeEditor";

import LanguageSelector
    from "../components/problem/LanguageSelector";

import RunSubmitButtons
    from "../components/problem/RunSubmitButtons";

import ResultPanel
    from "../components/problem/ResultPanel";

import {
    executeCode
}
    from "../services/executionService";

import { getStarterCode } from "../services/problemService";

import PublicTestCases
    from "../components/problem/PublicTestCases";

import {
    getPublicTestCases
}
    from "../services/problemService";

import {

    saveCode,
    loadCode

}
    from "../utils/editorStorage";
function ProblemPage() {

    const { id } =
        useParams();

    const [
        problem,
        setProblem
    ] = useState(null);

    const [
        language,
        setLanguage
    ] = useState(
        "JAVA"
    );

    const [
        code,
        setCode
    ] = useState(
        ""
    );

    const [
        testCases,
        setTestCases
    ] = useState([]);


    const [
        result,
        setResult
    ] = useState(null);

    const [
        loadingAction,
        setLoadingAction
    ] = useState(null);

    const [
        editorInitialized,
        setEditorInitialized
    ] = useState(false);

    const [isLoadingCode, setIsLoadingCode]
        = useState(false);

    useEffect(() => {

        fetchProblem();
        fetchPublicTestCases();

    }, []);

    useEffect(() => {

        if (id) {

            fetchStarterCode();

        }

    }, [
        language,
        id
    ]);
    const handleCodeChange =
        (newCode) => {

            setCode(
                newCode
            );

            if (
                !isLoadingCode
            ) {

                saveCode(
                    id,
                    language,
                    newCode
                );

            }

        }

    const fetchPublicTestCases =
        async () => {

            try {

                const data =
                    await getPublicTestCases(
                        id
                    );

                setTestCases(
                    data
                );

            }
            catch (error) {

                console.log(
                    error
                );

            }

        };


    const fetchStarterCode =
        async () => {

            try {

                setIsLoadingCode(
                    true
                );

                const starterCode =
                    await getStarterCode(
                        id,
                        language
                    );

                const savedCode =
                    loadCode(
                        id,
                        language
                    );

                setCode(

                    savedCode
                    ||

                    starterCode

                );

            }
            catch (error) {

                console.log(
                    error
                );

            }
            finally {

                setTimeout(() => {

                    setIsLoadingCode(
                        false
                    );

                }, 100);

            }

        }

    const handleExecution =
        async (mode) => {

            try {

                setLoadingAction(
                    mode
                );

                const data =
                    await executeCode({

                        problemId: id,
                        language,
                        mode,
                        code

                    });

                setResult(data);

            }
            catch (error) {

                console.log(error);

            }
            finally {

                setLoadingAction(
                    null
                );

            }

        }

    const fetchProblem =
        async () => {

            try {

                const data =
                    await getProblemById(
                        id
                    );

                setProblem(
                    data
                );

            }
            catch (error) {

                console.log(
                    error
                );

            }

        };

    if (!problem) {

        return (
            <div>
                Loading...
            </div>
        );

    }

    return (

        <div
            className="
grid
grid-cols-2
h-screen
"
        >

            <div
                className="
    p-6
    overflow-y-auto
    "
            >

                <h1
                    className="
        text-3xl
        font-bold
        "
                >

                    {problem.title}

                </h1>

                <p
                    className="
        mt-4
        "
                >

                    {problem.description}

                </p>
                <PublicTestCases
                    testCases={
                        testCases
                    }
                />

            </div>

            <div
                className="
    p-4
    "
            >

                <div
                    className="
        mb-4
        "
                >

                    <LanguageSelector
                        language={language}
                        setLanguage={setLanguage}
                    />
                    <RunSubmitButtons

                        loadingAction={
                            loadingAction
                        }

                        onRun={() =>
                            handleExecution(
                                "RUN"
                            )
                        }

                        onSubmit={() =>
                            handleExecution(
                                "SUBMIT"
                            )
                        }

                    />

                    <ResultPanel
                        result={result}
                    />

                </div>

                <CodeEditor
                    code={code}
                    setCode={handleCodeChange}
                    language={language}
                />

            </div>

        </div>

    );

}

export default ProblemPage;