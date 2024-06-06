
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Filler,
    Legend,
} from "chart.js";
import { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import { getHostMetrics } from "@/services/hostMetrics";

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Filler,
    Legend
);

export const options = {
    responsive: true,
    plugins: {
        title: {
            display: true,
            text: "Informacion del host",
        },
    },
    scales: {
        y: {
            beginAtZero: true,
        },
    },
};

function Metrics() {
    const [_, setData] = useState<any[]>([]);
    const [dataToChart, setDataToChart] = useState<any[]>([]);

    useEffect(() => {
        getHostMetrics().then((metrics) => {
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
            console.log("******DATA********", tranformData);
        });
    }, []);

    const labels = dataToChart.map((metric) => metric.dateRegistered);

    const dataChart = {
        labels,
        datasets: [
            {
                fill: true,
                label: "RAM",
                data: dataToChart.map((metric) => metric.ram / 1024),
                borderColor: "yellow",
                backgroundColor: "rgba(255, 255, 0, 0.5)",
            },
            {
                fill: true,
                label: "CPU",
                data: dataToChart.map((metric) => metric.cpu),
                borderColor: "red",
                backgroundColor: "rgba(255, 99, 132, 0.5)",
            },
            {
                fill: true,
                label: "Disk",
                data: dataToChart.map((metric) => metric.disk),
                borderColor: "rgb(75, 192, 192)",
                backgroundColor: "rgba(75, 192, 192, 0.5)",
            },
        ],
    };
    return (
        <div className="h-[50vh]  overflow-hidden">
            <Line options={options} data={dataChart} />;
        </div>
    );
}

export default Metrics;
