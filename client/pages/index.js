import React, { useState } from 'react'
// Sidebar
import { BsSearch } from 'react-icons/bs'
import { AiFillEnvironment } from "react-icons/ai"
import Activites from "../components/activities"
import Sidebar from '../components/sidebar'

export default function Home() {
  

  return (
    <>
      <div className='flex font-default bg-black-back'>

        {/* SIDEBAR */}
        <Sidebar />
        
        <div className='flex flex-col w-screen'>
          {/* NAVBAR */}
          <div className='flex justify-center bg-dark-grey h-20'>
            {/* Search Bar */}
            <div className='flex ml-auto w-96 items-center rounded-3xl bg-light-white my-5 border-white border-2'>
                <BsSearch className='text-white text-lg block float-left cursor-pointer mx-4'/>
                <input type={"search"} placeholder="Search" className='text-base bg-transparent w-full text-white focus:outline-none mr-3'></input>
            </div>
            {/* Create Event Button */}
            <div className='flex ml-auto items-center'>
              <button className='flex w-50 h-10 items-center rounded-3xl bg-blue-500 my-5 mr-5'>
                  <BsSearch className='text-white text-lg block float-left cursor-pointer mx-4'/>
                  <a className='text-base bg-transparent w-full text-white focus:outline-none mr-3'>Create Event</a>
              </button>
            </div>
          </div>
          {/* END OF NAVBAR */}
          <div className='ml-16 mt-16'>
            <Activites />
          </div>
        </div>
      </div>
    </>
  )
}
