import React, { useState } from 'react'
// Sidebar
import { BsArrowLeftShort, BsSearch } from 'react-icons/bs'
import { AiFillEnvironment } from "react-icons/ai"

export default function Home() {
  const [open, setOpen] = useState(true);

  return (
    <div className='flex'>
      <div className={`bg-dark-grey h-screen p-5 pt-8 ${open ? "w-72" : "w-20"} duration-300 relative`}>
          <BsArrowLeftShort 
              className={
                `bg-white text-dark-grey text-3xl rounded-full absolute -right-3 top-9 border border-dark-grey cursor-pointer ${!open && "rotate-180"}`
              }
              onClick={() => setOpen(!open)}
          />

          <div className='inline-flex'>
            <AiFillEnvironment className={`bg-amber-300 text-4xl rounded block float-left mr-3 duration-700 ${!open && "rotate-[360deg]"}`}/>
            <h1 className={`text-white origin-left font-medium text-2xl duration-300 ${!open && "scale-0"}`}>Sidebar</h1>
          </div>

          

          <ul className='pt-2'>

          </ul>
      </div>

      
      
      <div className='flex justify-center w-screen bg-dark-grey h-28'>
        <div className='flex w-80 items-center rounded-md bg-light-white my-8'>
              <BsSearch className='text-white text-lg block float-left cursor-pointer mx-4'/>
              <input type={"search"} placeholder="search" className='text-base bg-transparent w-full text-white focus:outline-none mr-3'></input>
        </div>

      </div>
    </div>
  )
}
