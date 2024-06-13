import { useStore } from "@/store/connection";
import { useEffect, useRef, useState } from "react";
import { FaSpinner } from "react-icons/fa";

function TerminalConn() {
  const iframeRef = useRef(null);
  const conn = useStore((state: any) => state.conn);
  const [url, setUrl] = useState(localStorage.getItem('url') || '');

  useEffect(() => {
    if (iframeRef.current) {
      iframeRef.current.focus();
    }
  }, []);

  useEffect(() => {
    if (conn === '/machineManagement') return;
    if (iframeRef.current && conn) {
      iframeRef.current.src = conn;
      iframeRef.current.focus();
      setUrl(conn);
      localStorage.setItem('url', conn);
    }
  }, [conn]);
  console.log(url)

  return (
    <div className=" w-full p-6 bg-white rounded-lg shadow-lg dark:bg-gray-800">
      <div className="flex flex-col items-center justify-center">
        <div className="relative w-full aspect-video rounded-lg overflow-hidden">
          <iframe
            ref={iframeRef}
            src={url} className="w-full h-full" />
          {!url && (
            <div className="absolute inset-0 flex items-center justify-center bg-gray-900/50 backdrop-blur-sm">
              <div className="text-center text-white">
                <FaSpinner className="animate-spin h-5 w-5" />
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default TerminalConn;