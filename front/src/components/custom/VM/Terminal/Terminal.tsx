
export default function Terminal() {
    return (

        <div className="max-w-3xl w-full p-6 bg-white rounded-lg shadow-lg dark:bg-gray-800">
            <div className="flex flex-col items-center justify-center">
                <div className="relative w-full aspect-video rounded-lg overflow-hidden">
                    <iframe src="https://example.com/virtual-machine" className="w-full h-full" />
                    <div className="absolute inset-0 flex items-center justify-center bg-gray-900/50 backdrop-blur-sm">
                        <div className="text-center text-white">
                            <h3 className="text-2xl font-bold mb-2">No virtual machine is running</h3>
                            <p className="text-lg">Please start a virtual machine to view it here.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )
}