import React, { useState } from 'react'
import { 
    BsArrowLeftShort,
    BsCalendarEvent, 
    BsPerson } from 'react-icons/bs'

export default function Sidebar() {
    const [open, setOpen] = useState(true);

    return (
        <div className={`bg-dark-grey relative h-screen pt-8 ${open ? "w-72" : "w-20"} duration-300`}>
        <BsArrowLeftShort 
            className={
              `bg-white text-dark-grey text-3xl rounded-full absolute -right-3 top-9 border border-dark-grey cursor-pointer ${!open && "rotate-180"}`
            }
            onClick={() => setOpen(!open)}
        />
        <div className='inline-flex'>
          {/* <AiFillEnvironment className={`bg-amber-300 text-4xl rounded block float-left mr-3 duration-700 ${!open && "rotate-[360deg]"}`}/> */}
          <h1 className={`text-white pl-5 origin-left font-medium text-2xl duration-300 ${!open && "scale-0"}`}>Actions</h1>
        </div>

        <ul className={`p-5 pb-4 mt-4 cursor-pointer hover:bg-gray-500`}>
          <li className={`flex ${!open && "grid h-9"}`}>
            <BsCalendarEvent className={`fill-white pb-1 text-3xl duration-700 ${!open && "rotate-[360deg] h-fit ml-1"}`}/>
            <p className={`text-white ml-auto duration-300 ${!open && "scale-0"}`}>Activities</p>
          </li>
        </ul>
        <ul className={`p-5 pb-4 cursor-pointer hover:bg-gray-500`}>
          <li className={`flex ${!open && "grid h-9"}`}>
            <BsPerson className={`fill-white pb-1 text-3xl duration-700 ${!open && "rotate-[360deg] text-4xl"}`}/>
            <p className={`text-white ml-auto text-1xl duration-300 ${!open && "scale-0"}`}>My Schedule</p>
          </li>
        </ul>
    </div>
    );
}