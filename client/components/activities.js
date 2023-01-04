import React, { useState } from 'react'

export default function Activies() {
    const activities = [
        {
            id: "1",
            title: "Basketball",
        },
        {
            id: "2",
            title: "Soccer",
        },
        {
            id: "3",
            title: "Badmington",
        },
        {
            id: "4",
            title: "Ping Pong",
        },
        {
            id: "5",
            title: "Basketball",
        },
    ]

    return (
        <div className="flex gap-10 flex-wrap">
            {activities.map((activity) => {
                return (
                    // Card
                    <div key={activity.id} className='flex rounded-2xl w-52 h-52 text-white bg-card-bg'>
                        {/* Title + Icon */}
                        <div className='pl-5 pt-4'>
                            <a>{activity.title}</a>
                        </div>
                        {/* Attendees */}
                        <div>
                        </div>
                        {/* Location + Time */}
                        <div>
                        </div>
                    </div>)
            })}
        </div>
    );
}