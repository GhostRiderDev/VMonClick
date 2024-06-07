import { Link } from "react-router-dom";
function Footer() {
    return (
        <footer className="bg-[#0a0a0aec] text-white py-8">
            <div className="container mx-auto px-4 flex flex-col md:flex-row items-center justify-between">
                <div className="flex items-center space-x-4">
                    <div className="text-2xl font-bold">
                        <MountainIcon className="h-8 w-8 text-blue-500" />
                        <span className="sr-only">VM Manager</span>
                    </div>
                    <div className="space-x-4">
                        <Link to="https://github.com/cos4h" className="hover:text-blue-500" >
                            cos4h
                        </Link>
                        <Link to="https://github.com/KeniBeck" className="hover:text-blue-500" >
                            Keni Beck
                        </Link>
                    </div>
                </div>
                <div className="flex items-center space-x-4 mt-4 md:mt-0">
                    <Link to="#" className="hover:text-blue-500" >
                        <TwitterIcon className="h-6 w-6" />
                    </Link>
                    <Link to="#" className="hover:text-orange-500" >
                        <GitlabIcon className="h-6 w-6" />
                    </Link>
                    <Link to="#" className="hover:text-blue-500" >
                        <LinkedinIcon className="h-6 w-6" />
                    </Link>
                </div>
            </div>
            <div className="container mx-auto px-4 mt-8 text-center text-sm text-gray-400">
                &copy; 2024 VMonClick. All rights reserved.
            </div>
        </footer >
    );

}
export default Footer;
function GitlabIcon(props) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="m22 13.29-3.33-10a.42.42 0 0 0-.14-.18.38.38 0 0 0-.22-.11.39.39 0 0 0-.23.07.42.42 0 0 0-.14.18l-2.26 6.67H8.32L6.1 3.26a.42.42 0 0 0-.1-.18.38.38 0 0 0-.26-.08.39.39 0 0 0-.23.07.42.42 0 0 0-.14.18L2 13.29a.74.74 0 0 0 .27.83L12 21l9.69-6.88a.71.71 0 0 0 .31-.83Z" />
        </svg>
    )
}
function LinkedinIcon(props) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="M16 8a6 6 0 0 1 6 6v7h-4v-7a2 2 0 0 0-2-2 2 2 0 0 0-2 2v7h-4v-7a6 6 0 0 1 6-6z" />
            <rect width="4" height="12" x="2" y="9" />
            <circle cx="4" cy="4" r="2" />
        </svg>
    )
}


function MountainIcon(props) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="m8 3 4 8 5-5 5 15H2L8 3z" />
        </svg>
    )
}
function TwitterIcon(props) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2C3 15.5.5 9.6 3 5c2.2 2.6 5.6 4.1 9 4-.9-4.2 4-6.6 7-3.8 1.1 0 3-1.2 3-1.2z" />
        </svg>
    )
}