export default function About() {
  return (
    <div className="flex flex-col gap-4">
      <h1 className="text-3xl font-semibold">About</h1>
      <p>
        Codicefun Blog is a corporate editorial blog maintained by the
        Codicefun team. We publish articles about engineering, products and the
        ideas behind the work we do.
      </p>
      <p className="text-zinc-600 dark:text-zinc-400">
        Authors draft articles, editors review them, and together we ship
        content readers can trust.
      </p>
    </div>
  );
}
