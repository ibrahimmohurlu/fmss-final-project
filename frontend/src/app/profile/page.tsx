import { auth } from "@/auth"
import { SubmitButton } from "@/components/SubmitButton";
import { fetchUserListings, toggleListingStatus } from "@/utils/fetchListings";
import { fetchUserPackages } from "@/utils/fetchPackages";
import Link from "next/link";
type ProfilePageProps = { params: { [key: string]: string }, searchParams: { [key: string]: string } }

export default async function ProfilePage({ searchParams }: ProfilePageProps) {
    const session = await auth();
    const tab: string = searchParams["tab"] ?? "package"
    const email = session?.user?.email ?? "";
    const password = session?.user?.password ?? "";
    const userPackages = await fetchUserPackages(email, password)
    const userListings = await fetchUserListings(email, password)
    return (
        <main className="flex min-h-screen flex-col items-center justify-between p-24">

            <div className="w-full bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">

                <div className="flex flex-col items-center py-6">
                    <h5 className="mb-1 text-xl font-medium text-gray-900 dark:text-white">Email: {session?.user?.email}</h5>
                </div>
                <div className="sm:hidden">
                    <label htmlFor="tabs" className="sr-only">Select tab</label>
                    <select id="tabs" className="bg-gray-50 border-0 border-b border-gray-200 text-gray-900 text-sm rounded-t-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option>My Packages</option>
                        <option>My Listings</option>
                    </select>
                </div>
                <ul className="hidden text-sm font-medium text-center text-gray-500 divide-x divide-gray-200 rounded-lg sm:flex dark:divide-gray-600 dark:text-gray-400 rtl:divide-x-reverse" id="fullWidthTab" data-tabs-toggle="#fullWidthTabContent" role="tablist">
                    <Link href={"/profile?tab=packages"} className="w-full">
                        <button id="stats-tab" data-tabs-target="#stats" type="button" role="tab" aria-controls="stats" aria-selected="true" className="inline-block w-full p-4 rounded-ss-lg bg-gray-50 hover:bg-gray-100 focus:outline-none dark:bg-gray-700 dark:hover:bg-gray-600">My Packages</button>
                    </Link>
                    <Link href={"/profile?tab=listings"} className="w-full">
                        <button id="about-tab" data-tabs-target="#about" type="button" role="tab" aria-controls="about" aria-selected="false" className="inline-block w-full p-4 bg-gray-50 hover:bg-gray-100 focus:outline-none dark:bg-gray-700 dark:hover:bg-gray-600">My Listings</button>
                    </Link>
                </ul>
                <div id="fullWidthTabContent" className="border-t border-gray-200 dark:border-gray-600">
                    <div data-selected={tab === "packages"} className="hidden data-[selected=true]:block space-y-4 p-4 bg-white rounded-lg md:p-8 dark:bg-gray-800" id="stats" role="tabpanel" aria-labelledby="stats-tab">
                        {userPackages.map(p => {
                            return <div key={p.id} className="px-4 py-2 flex flex-col bg-gray-600 rounded-md">
                                <span>ID:{p.id}</span>
                                <span>Remaining allowance: {p.remaining_listing_allowance}</span>
                                <span>Valid until {new Date(p.expiration_date).toLocaleDateString()}</span>
                                <span>Purchased at {new Date(p.purchase_date).toLocaleDateString()}</span>
                                <span> Status: {p.is_confirmed ?
                                    (<span className="bg-green-100 text-green-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-green-900 dark:text-green-300">Confirmed</span>)
                                    :
                                    (<span className="bg-yellow-100 text-yellow-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-full dark:bg-yellow-900 dark:text-yellow-300">Pending</span>)}
                                </span>
                            </div>
                        })}
                    </div>
                    <div data-selected={tab === "listings"} className="hidden data-[selected=true]:block space-y-4 p-4 bg-white rounded-lg md:p-8 dark:bg-gray-800" id="about" role="tabpanel" aria-labelledby="about-tab">
                        {userListings.map(l => {
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
                                    {l.status === "IN_REVIEW" ? null : (
                                        <form action={toggleListingStatus.bind(null, email, password, l.id)}>
                                            <SubmitButton className="p-1 bg-blue-700 rounded-l text-sm">
                                                Toggle Status
                                            </SubmitButton>
                                        </form>
                                    )}
                                </span>

                            </div>
                        })}
                    </div>

                </div>
            </div>


        </main>
    )
}