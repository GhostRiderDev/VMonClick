import MenuPrincipal from "./navBar/MenuPrincipal";
import MetricsTesting from "../../metrics/MetricsTesting";
import { useEffect, useState } from "react";
import Footer from "@/Footer";
import SidebarButtons from "../SidebarButtons";
import { getHostMetrics } from "@/services/hostMetrics";


function Menu() {
    const [currentPage, setCurrentPage] = useState("Inicio");
    const [, setData] = useState([]);
    const [dataToChart, setDataToChart] = useState([]);
    const token = localStorage.getItem("token");



    useEffect(() => {

        getHostMetrics(token).then((metrics) => {
            setData(
                metrics.sort((a, b) => {
                    const dateNum =
                        new Date(a.dateRegistered).getTime() -
                        new Date(b.dateRegistered).getTime();
                    return dateNum;
                })
            );
            const tranformData = metrics.slice(metrics.length - 10, metrics.length);
            setDataToChart(tranformData);
        });
    }, []);

    const cpuData = dataToChart.map((item, index) => ({ x: index, y: item.cpu }));
    const ramData = dataToChart.map((item, index) => ({
        x: index,
        y: item.ram / 1024,
    }));
    const diskData = dataToChart.map((item, index) => ({
        x: index,
        y: item.disk,
    }));


    return (
        <div className=" bg-slate-900  w-full">
            <div className=" flex flex-col top-0 left-0 right-0 z-50 w-full">
                <div className="sticky top-0 z-50"><MenuPrincipal /></div>

                <div className=" w-full flex flex-row">
                    <div className="w-[300px]  sm:block hidden bg-slate-900 lg:h-[683px] lg:py-8 pt-2 sticky top-12 md:flex justify-center px-2">
                        <SidebarButtons currentPage={currentPage} setCurrentPage={setCurrentPage} />
                    </div>
                    <div className="w-full bg-slate-950 px-8 pb-4 space-y-6 lg:py-8 h-max rounded-md ">
                        <div className="w-full text-center space-y-2 lg:text-left lg:space-y-10 lg:pl-[18px]">
                            <h1 className="text-3xl pl-2 pt-4 ">
                                <strong className="text-white">Bienvenido</strong>
                            </h1>
                            <p className="text-2xl text-white">
                                Gestiona tus maquinas virtuales de una manera mas sencilla,comoda
                                y segura.
                            </p>
                        </div>

                        <section>
                            <div className="flex flex-col bg-slate-200 dark:bg-gray-800 rounded-b-md">
                                <div className="bg-sky-400 dark:bg-gray-900 shadow lg:h-[160px] py-4 rounded-t-md">
                                    <div className="max-w-6xl mx-auto py-4 px-8 sm:px-6 lg:px-8 pt-8 pb-8 ">
                                        <h1 className="text-2xl font-bold text-gray-900 dark:text-gray-100">
                                            Cosumo actual de tu maquina
                                        </h1>
                                        <p className="text-gray-600 dark:text-gray-800">
                                            Monitoriando tu CPU, RAM y disco usados en tu maquina.
                                        </p>
                                    </div>
                                </div>
                                <MetricsTesting cpuData={cpuData} ramData={ramData} diskData={diskData} />
                            </div>
                        </section>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}
export default Menu;
