import { useEffect, useRef } from "react";

function TerminalConn() {
  const iframeRef = useRef(null);

  useEffect(() => {
    if (iframeRef.current) {
      iframeRef.current.focus();
    }
  }, []);

  return (
    <div className="bg-red-500 flex justify-center items-center h-screen">
      <iframe
        ref={iframeRef}
        src="http://localhost:6060/#/client/NABjAHBvc3RncmVzcWw="
        title="Remote Desktop"
        width="50%"
        height="80%"
        className="rounded-md"
      />
    </div>
  );
}

export default TerminalConn;
