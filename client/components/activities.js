import React, { useState } from 'react'

export default function Activies() {
    const activities = [
        {
            id: "1",
            title: "Basketball",
            location: "CIF",
            details: "July 19 @ 2:30pm"
        },
        {
            id: "2",
            title: "Soccer",
            location: "CIF",
            details: "July 19 @ 2:30pm"
        },
        {
            id: "3",
            title: "Badmington",
            location: "CIF",
            details: "July 19 @ 2:30pm"
        },
        {
            id: "4",
            title: "Ping Pong",
            location: "CIF",
            details: "July 19 @ 2:30pm"
        },
        {
            id: "5",
            title: "Basketball",
            location: "CIF",
            details: "July 19 @ 2:30pm"
        },
    ]

    return (
        <div className="flex gap-10 flex-wrap">
            {activities.map((activity) => {
                return (
                    // Card
                    <div key={activity.id} className='flex flex-col rounded-2xl w-52 h-52 text-white bg-card-bg'>
                        {/* Title + Icon */}
                        <div className='pl-5 pt-4'>
                            <a>{activity.title}</a>
                        </div>
                            {/* Attendees */}
                        <div className='px-5 pt-4 text-xs'>
                            <a>{activity.location}</a><br/>
                            <a>{activity.details}</a>
                        </div>
                        <div>

                        </div>
                    </div>)
            })}
        </div>
    );
}