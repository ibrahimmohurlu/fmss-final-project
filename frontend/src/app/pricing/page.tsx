import PricingCard from "@/components/PricingCard";
import fetchPackages from "@/utils/fetchPackages";

export default async function PricingPage() {
    const packages = await fetchPackages();
    return (
        <main className="flex flex-wrap min-h-screen items-center justify-center gap-8 p-24">

            {packages.map(p => {
                return <PricingCard key={p.id} peckage={p} />
            })}

        </main>
    )
}