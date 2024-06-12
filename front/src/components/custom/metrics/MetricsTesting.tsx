import {
    Card,
    CardHeader,
    CardTitle,
    CardContent,
    CardFooter,
} from "@/components/ui/card";
import { ResponsiveLine } from "@nivo/line";
import { FaSpinner } from "react-icons/fa";


function MetricsTesting({ cpuData, ramData, diskData }) {
    if (!cpuData || !ramData || !diskData) {
        return (<div className="pl-6 pt-2 pb-2"><FaSpinner className="animate-spin h-5 w-5 " /></div>
        );
    }

    return (
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
                    <CardFooter></CardFooter>
                </Card>
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
