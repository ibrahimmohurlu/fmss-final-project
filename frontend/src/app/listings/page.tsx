import { fetchListings } from "@/utils/fetchListings"

export default async function ListingPage() {
    const listings = await fetchListings();

    return (
        <main className="flex min-h-screen flex-col items-center justify-between p-24">
            {listings.map(l => {
                return <div key={l.id} className="px-4 py-2 flex flex-col bg-gray-600 rounded-md">
                    <span>ID:{l.id}</span>
                    <span>Title: {l.title}</span>
                    <span>Description: {l.description}</span>
                    <span>Price: {l.price}</span>
                    <span>Created at: {new Date(l.created_at).toLocaleDateString()}</span>
                    <span> Status: {l.status === "ACTIVE" ?
                        (<span className="bg-green-100 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-green-900 dark:text-green-300">ACTIVE</span>)
                        : l.status === "IN_REVIEW" ?
                            (<span className="bg-yellow-100 text-yellow-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-yellow-900 dark:text-yellow-300">IN_REVIEW</span>)
                            : (<span className="bg-red-100 text-red-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-red-900 dark:text-red-300">IN_ACTIVE</span>)}
                        {/* {l.status === "IN_REVIEW" ? null : (
                            <form action={toggleListingStatus.bind(null, email, password, l.id)}>
                                <SubmitButton className="p-1 bg-blue-700 rounded-l text-sm">
                                    Toggle Status
                                </SubmitButton>
                            </form>
                        )} */}
                    </span>

                </div>
            })}
        </main>
    )
}