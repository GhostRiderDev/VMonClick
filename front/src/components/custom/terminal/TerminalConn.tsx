import { useStore } from "@/store/connection";
import { useEffect, useRef } from "react";

function TerminalConn() {
  const iframeRef = useRef(null);
  const conn = useStore((state: any) => state.conn);

  useEffect(() => {
    if (iframeRef.current) {
      iframeRef.current.focus();
    }
  }, []);

  useEffect(() => {
    if (iframeRef.current) {
      iframeRef.current.src = conn;
    }
  }, [conn]);

  return (
    <div className="bg-red-500 flex justify-center items-center h-screen">
      <iframe
        ref={iframeRef}
        src={conn}
        title="Remote Desktop"
        width="50%"
        height="80%"
        className="rounded-md"
      />
    </div>
  );
}

export default TerminalConn;
