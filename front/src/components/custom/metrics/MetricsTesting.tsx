import {
    Card,
    CardHeader,
    CardTitle,
    CardContent,
    CardFooter,
} from "@/components/ui/card";
import { ResponsiveLine } from "@nivo/line";
import { useState, useEffect } from "react";
import { getHostMetrics } from "@/services/hostMetrics";
function MetricsTesting() {
    const [, setData] = useState([]);
    const [dataToChart, setDataToChart] = useState([]);

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
        <div className="flex flex-col bg-sky-300 dark:bg-gray-800 rounded-md">
            <div className="bg-sky-400 dark:bg-gray-900 shadow lg:h-[160px] py-4 rounded-md">
                <div className="max-w-6xl mx-auto py-4 px-8 sm:px-6 lg:px-8 pt-8 pb-8 ">
                    <h1 className="text-2xl font-bold text-gray-900 dark:text-gray-100">
                        Cosumo actual de tu maquina
                    </h1>
                    <p className="text-gray-600 dark:text-gray-800">
                        Monitoriando tu CPU, RAM y disco usados en tu maquina.
                    </p>
                </div>
            </div>
            <div className="flex-1 max-w-6xl w-full mx-auto py-8 px-4 sm:px-6 lg:px-8">
                <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
                    <Card className=" w-full   mx-auto ">
                        <CardHeader>
                            <CardTitle>CPU</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <LineChart id="CPU" className="aspect-[23/9]" data={cpuData} />
                        </CardContent>
                        <CardFooter></CardFooter>
                    </Card>
                    <Card className="w-full   mx-auto">
                        <CardHeader>
                            <CardTitle>Memoria RAM</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <LineChart id="RAM" className="aspect-[23/9]" data={ramData} />
                        </CardContent>
                        <CardFooter></CardFooter>
                    </Card>
                    <Card className="w-full  mx-auto">
                        <CardHeader>
                            <CardTitle>Disk</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <LineChart id="Disk" className="aspect-[23/9]" data={diskData} />
                        </CardContent>
                        <CardFooter>
                            {/* <div className="flex items-center justify-between">
                                <Button variant="ghost" size="sm">
                                    1h
                                </Button>
                                <Button variant="ghost" size="sm">
                                    6h
                                </Button>
                                <Button variant="ghost" size="sm">
                                    1d
                                </Button>
                                <Button variant="ghost" size="sm">
                                    1w
                                </Button>
                            </div> */}
                        </CardFooter>
                    </Card>
                </div>
            </div>
        </div>
    );
}
export default MetricsTesting;
function LineChart({ id, data, ...props }) {
    return (
        <div {...props}>
            <ResponsiveLine
                data={[
                    {
                        id: id,
                        data: data,
                    },
                ]}
                margin={{ top: 10, right: 10, bottom: 30, left: 40 }}
                xScale={{
                    type: "point",
                }}
                yScale={{
                    type: "linear",

                }}
                axisTop={null}
                axisRight={null}
                axisBottom={{
                    tickSize: 0,
                    tickPadding: 16,
                }}
                axisLeft={{
                    tickSize: 0,
                    tickValues: 5,
                    tickPadding: 16,
                }}
                colors={["#2563eb", "#e11d48"]}
                pointSize={7}
                useMesh={true}
                gridYValues={10}
                theme={{
                    tooltip: {
                        chip: {
                            borderRadius: "9999px",
                        },
                        container: {
                            fontSize: "12px",
                            textTransform: "capitalize",
                            borderRadius: "6px",
                        },
                    },
                    grid: {
                        line: {
                            stroke: "#f3f4f6",
                        },
                    },
                }}
                role="application"
                enableArea={true}
            />
        </div>
    );
}
